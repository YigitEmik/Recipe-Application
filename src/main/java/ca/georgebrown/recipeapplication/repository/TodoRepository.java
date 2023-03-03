package ca.georgebrown.recipeapplication.repository;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAllByRecipe(Recipe recipe);
}
