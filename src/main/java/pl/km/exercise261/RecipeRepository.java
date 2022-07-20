package pl.km.exercise261;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findTop10ByOrderByIdDesc();

    List<Recipe> findRecipeByCategoriesOrderByName(RecipeCategory category);

    List<Recipe> findTop5ByOrderByLikesCounterDesc();

    List<Recipe> findAllByOrderByName();

}
