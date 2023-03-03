package ca.georgebrown.recipeapplication.service.impl;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.model.Todo;
import ca.georgebrown.recipeapplication.model.User;
import ca.georgebrown.recipeapplication.repository.TodoRepository;
import ca.georgebrown.recipeapplication.service.TodoService;
import ca.georgebrown.recipeapplication.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {


    private final UserService userService;
    private final TodoRepository todoRepository;

    public TodoServiceImpl(UserService userService, TodoRepository todoRepository) {
        this.userService = userService;
        this.todoRepository = todoRepository;
    }


    @Override
    public void createTodoForCurrentUser(Todo todo, Recipe recipe) {
        User user = userService.getCurrentUser();
        List<Todo> todos = user.getTodos();
        if (Objects.isNull(todos)) {
            todos = new ArrayList<>();
        }
        todo.setRecipe(recipe);

        todoRepository.save(todo);
        todos.add(todo);
        user.setTodos(todos);
        userService.save(user);
    }

    @Override
    public List<Todo> getTodosByRecipe(Recipe recipe) {
        return todoRepository.findAllByRecipe(recipe);
    }

    @Override
    public void deleteTodoList(List<Todo> todos) {
        User user = userService.getCurrentUser();
        List<Todo> userTodos = user.getTodos();

        userTodos = userTodos.stream().filter(x-> todos.stream().noneMatch(y-> y.getId().equals(x.getId()))).collect(Collectors.toList());
        user.setTodos(userTodos);
        userService.save(user);
        todoRepository.deleteAll(todos);
    }

    @Override
    public void saveTodos(Todo todo) {
        todoRepository.save(todo);
    }
}
