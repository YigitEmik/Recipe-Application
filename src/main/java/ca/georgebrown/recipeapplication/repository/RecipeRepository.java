package ca.georgebrown.recipeapplication.repository;

import ca.georgebrown.recipeapplication.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAllByNameContainingIgnoreCaseOrIngredientsContainingIgnoreCaseOrderByCreationDateDesc(String name, String ingredients);
}
