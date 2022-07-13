package pl.km.exercise261;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ingredient WHERE recipe_id IS NULL", nativeQuery = true)
    void deleteUnusedIngredients();
}
