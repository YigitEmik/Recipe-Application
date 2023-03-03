package ca.georgebrown.recipeapplication.service;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.model.Todo;

import java.util.List;

public interface TodoService {

    void createTodoForCurrentUser(Todo todo, Recipe recipe);

    List<Todo> getTodosByRecipe(Recipe recipe);

    void deleteTodoList(List<Todo> todos);

    void saveTodos(Todo todo);
}
