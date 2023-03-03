package ca.georgebrown.recipeapplication.service;

import ca.georgebrown.recipeapplication.model.Recipe;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipeService {

    Recipe getRecipeById(Integer id);

    Page<Recipe> findPaginated(int pageNo, int pageSize);

    List<Recipe> searchRecipeByNameOrIngredients(String search);

    void deleteRecipeById(Integer id);
}
