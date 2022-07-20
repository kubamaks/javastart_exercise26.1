package pl.km.exercise261;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import static pl.km.exercise261.ApplicationDisplayTemplates.PICTURE_FOR_RECIPE_EDIT_AND_UPDATE;
import static pl.km.exercise261.ApplicationDisplayTemplates.TITLE_FOR_NEW_RECIPE;

@Controller
public class FormController {

    private final RecipeService recipeService;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final IngredientRepository ingredientRepository;

    public FormController(RecipeService recipeService,
                          RecipeCategoryRepository recipeCategoryRepository, IngredientRepository ingredientRepository) {
        this.recipeService = recipeService;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    private void addCategoriesToModel(Model model) {
        List<RecipeCategory> categories = recipeCategoryRepository.findAll();
        model.addAttribute("categories", categories);
    }

    @GetMapping("/nowy")
    public String addNewRecipe(Model model) {
        Recipe recipe = new Recipe();
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ingredients.add(new Ingredient());
        }
        recipe.setIngredients(ingredients);
        model.addAttribute("recipe", recipe);
        model.addAttribute("pictureName", PICTURE_FOR_RECIPE_EDIT_AND_UPDATE);
        model.addAttribute("title", TITLE_FOR_NEW_RECIPE);
        return "form";
    }

    @PostMapping("/przepis/skladnik")
    public String addOrDeleteIngredient(@ModelAttribute("recipe") Recipe recipe,
                                        @RequestParam("action") String action,
                                        @RequestParam(value = "ingId", required = false) Long ingId,
                                        Model model) {
        if (action.equals("add")) {
            recipe.addIngredient(new Ingredient());
        }
        if (action.equals("remove") && ingId != null) {
            recipe.removeIngredientByIndex(ingId.intValue());
        }
        model.addAttribute("recipe", recipe);
        String title;
        if (recipe.getId() != null) {
            title = "Edycja przepisu: " + recipe.getName();
        } else {
            title = ApplicationDisplayTemplates.TITLE_FOR_NEW_RECIPE;
        }
        model.addAttribute("title", title);
        model.addAttribute("pictureName", PICTURE_FOR_RECIPE_EDIT_AND_UPDATE);
        return "form";
    }

    @PostMapping("/zapisz")
    public String saveRecipe(
            @ModelAttribute("recipe") Recipe recipe) {
        recipeService.clearUnfilledIngredients(recipe);
        recipeService.save(recipe);
        ingredientRepository.deleteUnusedIngredients();
        return "redirect:/przepis/" + recipe.getId();
    }

    @GetMapping("/przepis/{id}/edytuj")
    public String editRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("pictureName", PICTURE_FOR_RECIPE_EDIT_AND_UPDATE);
        model.addAttribute("recipe", recipe);
        String title = "Edycja przepisu: " + recipe.getName();
        model.addAttribute("title", title);
        return "form";
    }
}
