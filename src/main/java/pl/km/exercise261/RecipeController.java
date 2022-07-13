package pl.km.exercise261;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static pl.km.exercise261.ApplicationDisplayTemplates.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(RecipeService recipeService,
                            RecipeCategoryRepository recipeCategoryRepository,
                            IngredientRepository ingredientRepository) {
        this.recipeService = recipeService;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    private void addCategoriesToModel(Model model) {
        List<RecipeCategory> categories = recipeCategoryRepository.findAll();
        model.addAttribute("categories", categories);
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Recipe> recipes = recipeService.get10Newest();
        List<String> prepTimes = recipeService.getStringedTimeListFromRecipesList(recipes);
        model.addAttribute("recipes", recipes);
        model.addAttribute("prepTimes", prepTimes);
        model.addAttribute("title", TITLE_HOME);
        model.addAttribute("pictureName", PICTURE_HOME);
        return "index";
    }

    @GetMapping("/popularne")
    public String showMostLikedRecipes(Model model) {
        List<Recipe> recipes = recipeService.get5MostPopular();
        model.addAttribute("recipes", recipes);
        List<String> prepTimes = recipeService.getStringedTimeListFromRecipesList(recipes);
        model.addAttribute("prepTimes", prepTimes);
        model.addAttribute("title", TITLE_MOST_POPULAR);
        model.addAttribute("pictureName", PICTURE_MOST_POPULAR);
        return "index";
    }

    @GetMapping("/kategoria/{id}")
    public String listRecipesByCategories(@PathVariable Long id, Model model) {
        Optional<RecipeCategory> recipeCategoryOptional = recipeCategoryRepository.findById(id);
        if (recipeCategoryOptional.isPresent()) {
            RecipeCategory category = recipeCategoryOptional.get();
            model.addAttribute("category", category);
            List<Recipe> recipes = recipeService.getByCategoryOrderedByName(category);
            model.addAttribute("recipes", recipes);
            List<String> prepTimes = recipeService.getStringedTimeListFromRecipesList(recipes);
            model.addAttribute("prepTimes", prepTimes);
            String title = category.getName() + " : " + category.getDescription();
            String pictureName = PICTURE_COMMON_NAME_FOR_CATEGORIES + id;
            model.addAttribute("title", title);
            model.addAttribute("pictureName", pictureName);
            return "index";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/kategoria/wszystkie")
    public String listAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.getAllOrderedByName();
        List<String> prepTimes = recipeService.getStringedTimeListFromRecipesList(recipes);
        model.addAttribute("prepTimes", prepTimes);
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", TITLE_FOR_ALL_CATEGORIES);
        model.addAttribute("pictureName", PICTURE_FOR_ALL_CATEGORIES);
        return "index";
    }

    @GetMapping("/przepis/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.getById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipe", recipe);
            String preparationTime = recipeService.getStringedTimeFromPreparationTime(recipe.getPreparationTime());
            model.addAttribute("preparationTime", preparationTime);
            String pictureName = "list";
            model.addAttribute("pictureName", pictureName);
            model.addAttribute("title", recipe.getName());
            List<RecipeCategory> categories = recipe.getCategories();
            model.addAttribute("categories", categories);
            List<Ingredient> ingredients = recipe.getIngredients();
            model.addAttribute("ingredients", ingredients);
            return "recipe";
        }
        return "redirect:/";
    }

    @GetMapping("/przepis/{id}/lubie")
    public String addLikeToRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.getById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.setLikesCounter(recipe.getLikesCounter() + 1);
            recipeService.save(recipe);
            return "redirect:/przepis/" + id;
        }
        return "redirect:/";
    }

    @GetMapping("/przepis/{id}/usun")
    public String deleteRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipeOptional = recipeService.getById(id);
        if (recipeOptional.isPresent()) {
            recipeService.deleteById(id);
        }
        return "redirect:/";
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

    @PostMapping("/przepis/dodajskladnik")
    public String addNewIngredient(@ModelAttribute("recipe") Recipe recipe,
                                   Model model) {
//        List<RecipeCategory> categories = recipeCategoryRepository.findAll();
//        model.addAttribute("categories", categories);
        recipe.addIngredient(new Ingredient());
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

    @PostMapping("/przepis/usunskladnik/{index}")
    public String removeIngredientByIndex(@ModelAttribute("recipe") Recipe recipe,
                                          @PathVariable int index,
                                          Model model) {
        recipe.removeIngredientByIndex(index);
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
            @ModelAttribute("recipe") Recipe recipe,
            @RequestParam(value = "inputCategories", required = false) Long[] inputCategories) {
        if (inputCategories != null) {
            for (int i = 0; i < inputCategories.length; i++) {
                if (recipeCategoryRepository.findById(inputCategories[i]).isPresent()) {
                    RecipeCategory category = recipeCategoryRepository.findById(inputCategories[i]).orElseThrow();
                    recipe.addCategory(category);
                }
            }
        }
        recipeService.clearUnfilledIngredients(recipe);
        recipeService.save(recipe);
        ingredientRepository.deleteUnusedIngredients();
        return "redirect:/przepis/" + recipe.getId();
    }

    @GetMapping("/przepis/{id}/edytuj")
    public String editRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.getById(id);
        model.addAttribute("pictureName", PICTURE_FOR_RECIPE_EDIT_AND_UPDATE);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipe", recipe);
            String title = "Edycja przepisu: " + recipe.getName();
            model.addAttribute("title", title);
            return "form";
        } else {
            return "redirect:/";
        }
    }
}



