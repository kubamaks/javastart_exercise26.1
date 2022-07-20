package pl.km.exercise261;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeCategoryRepository recipeCategoryRepository;

    public RecipeController(RecipeService recipeService,
                            RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeService = recipeService;
        this.recipeCategoryRepository = recipeCategoryRepository;
    }

    @GetMapping("/przepis/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("recipe", recipe);
        String pictureName = "list";
        model.addAttribute("pictureName", pictureName);
        model.addAttribute("title", recipe.getName());
        List<RecipeCategory> categories = recipe.getCategories();
        model.addAttribute("categories", categories);
        List<Ingredient> ingredients = recipe.getIngredients();
        model.addAttribute("ingredients", ingredients);
        return "recipe";
    }

    @GetMapping("/przepis/{id}/lubie")
    public String addLikeToRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getById(id).orElseThrow(NotFoundException::new);
        recipe.setLikesCounter(recipe.getLikesCounter() + 1);
        recipeService.save(recipe);
        return "redirect:/przepis/" + id;
    }

    @GetMapping("/przepis/{id}/usun")
    public String deleteRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getById(id).orElseThrow(NotFoundException::new);
        recipeService.deleteById(id);
        return "redirect:/";
    }
}