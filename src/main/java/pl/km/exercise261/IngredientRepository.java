package pl.km.exercise261;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Query(value = "DELETE FROM ingredient WHERE recipe_id IS NULL", nativeQuery = true)
    public void deleteUnusedIngredients();
}
