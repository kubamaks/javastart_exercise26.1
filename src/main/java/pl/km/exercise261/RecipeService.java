package pl.km.exercise261;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getAll() {
        return repository.findAll();
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

    public String getPatternedTimeValueFromNumbers(int hours, int minutes) {
        return getFormattedTimeUnitFromNumber(hours) +
                ":" +
                getFormattedTimeUnitFromNumber(minutes) +
                ":00";
    }

    public String getStringedTimeFromPreparationTime(int preparationTime) {
        String hours;
        String minutes;
        if (preparationTime < 60) {
            hours = "00";
            minutes = getAtLeastTwoDigitalStringFromNumber(preparationTime);
        } else {
            minutes = getAtLeastTwoDigitalStringFromNumber(preparationTime % 60);
            hours = getAtLeastTwoDigitalStringFromNumber(preparationTime / 60);
        }
        String result = hours + ":" + minutes;
        return result;
    }

    private String getAtLeastTwoDigitalStringFromNumber(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return String.valueOf(number);
        }
    }

    public List<String> getStringedTimeListFromRecipesList(List<Recipe> recipes) {
        return recipes.stream().map(r -> getStringedTimeFromPreparationTime(r.getPreparationTime()))
                .collect(Collectors.toCollection(ArrayList<String>::new));
    }

    public void clearUnfilledIngredients(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        List<Ingredient> clearedList = ingredients.stream().filter(i -> !i.getName().equals("")).toList();
        List<Ingredient> clearedArrayList = new ArrayList<>(clearedList);
        recipe.setIngredients(clearedArrayList);
    }

    private String getFormattedTimeUnitFromNumber(int number) {
        if (number / 10 > 0) {
            return String.valueOf(number);
        } else {
            return "0" + number;
        }
    }
}
