package ca.georgebrown.recipeapplication.controller;

import ca.georgebrown.recipeapplication.model.Todo;
import ca.georgebrown.recipeapplication.service.RecipeService;
import ca.georgebrown.recipeapplication.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final RecipeService recipeService;

    public TodoController(TodoService todoService, RecipeService recipeService) {
        this.todoService = todoService;
        this.recipeService = recipeService;
    }

    @GetMapping("/create/{recipeId}")
    public String crateTodoForm(@PathVariable Integer recipeId, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("todo", new Todo());
        return "todo/create-todo";
    }

    @PostMapping("/create/{recipeId}")
    public String createTodo(@PathVariable Integer recipeId, Todo todo) {
        todoService.createTodoForCurrentUser(todo, recipeService.getRecipeById(recipeId));
        return "redirect:/user/my-todos";
    }

}
