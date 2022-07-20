package pl.km.exercise261;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public Optional<Recipe> getById(Long id) {
        return repository.findById(id);
    }

    public List<Recipe> get10Newest() {
        return repository.findTop10ByOrderByIdDesc();
    }

    public List<Recipe> getByCategoryOrderedByName(RecipeCategory category) {
        return repository.findRecipeByCategoriesOrderByName(category);
    }

    public List<Recipe> get5MostPopular() {
        return repository.findTop5ByOrderByLikesCounterDesc();
    }

    public List<Recipe> getAllOrderedByName() {
        return repository.findAllByOrderByName();
    }

    public void save(Recipe recipe) {
        repository.save(recipe);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void clearUnfilledIngredients(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        List<Ingredient> clearedList = ingredients.stream().filter(i -> !i.getName().equals("")).toList();
        List<Ingredient> clearedArrayList = new ArrayList<>(clearedList);
        recipe.setIngredients(clearedArrayList);
    }
}
