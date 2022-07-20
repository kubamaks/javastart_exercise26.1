package pl.km.exercise261;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static pl.km.exercise261.ApplicationDisplayTemplates.*;

@Controller
public class IndexController {
    private final RecipeService recipeService;
    private final RecipeCategoryRepository recipeCategoryRepository;

    public IndexController(RecipeService recipeService,
                           RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeService = recipeService;
        this.recipeCategoryRepository = recipeCategoryRepository;
    }

    @ModelAttribute
    private void addCategoriesToModel(Model model) {
        List<RecipeCategory> categories = recipeCategoryRepository.findAll();
        model.addAttribute("categories", categories);
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Recipe> recipes = recipeService.get10Newest();
        List<RecipeCategory> categories = recipeCategoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", TITLE_HOME);
        model.addAttribute("pictureName", PICTURE_HOME);
        return "index";
    }

    @GetMapping("/popularne")
    public String showMostLikedRecipes(Model model) {
        List<Recipe> recipes = recipeService.get5MostPopular();
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", TITLE_MOST_POPULAR);
        model.addAttribute("pictureName", PICTURE_MOST_POPULAR);
        return "index";
    }

    @GetMapping("/kategoria/{id}")
    public String listRecipesByCategories(@PathVariable Long id, Model model) {
        RecipeCategory category = recipeCategoryRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("category", category);
        List<Recipe> recipes = recipeService.getByCategoryOrderedByName(category);
        model.addAttribute("recipes", recipes);
        String title = category.getName() + " : " + category.getDescription();
        String pictureName = PICTURE_COMMON_NAME_FOR_CATEGORIES + id;
        model.addAttribute("title", title);
        model.addAttribute("pictureName", pictureName);
        return "index";
    }

    @GetMapping("/kategoria/wszystkie")
    public String listAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.getAllOrderedByName();
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", TITLE_FOR_ALL_CATEGORIES);
        model.addAttribute("pictureName", PICTURE_FOR_ALL_CATEGORIES);
        return "index";
    }
}
