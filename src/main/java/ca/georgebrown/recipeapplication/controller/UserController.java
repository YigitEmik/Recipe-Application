package ca.georgebrown.recipeapplication.controller;

import ca.georgebrown.recipeapplication.model.Todo;
import ca.georgebrown.recipeapplication.model.User;
import ca.georgebrown.recipeapplication.request.SearchRequest;
import ca.georgebrown.recipeapplication.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String updateInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "/user/user-profile";
    }

    @GetMapping("/favorites")
    public String getFavoriteRecipes(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("listRecipes", user.getFavoriteRecipes());
        model.addAttribute("search", new SearchRequest());
        return "recipes/favorite-recipes-list";
    }


    @GetMapping("/my-recipes")
    public String myRecipes(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("listRecipes", user.getRecipes());
        return "recipes/my-recipes";
    }

    @GetMapping("/my-todos")
    public String getMyTodos(Model model) {
        User user  = userService.getCurrentUser();
        user.getTodos().sort(Comparator.comparing(Todo::getDate));
        model.addAttribute("todos", user.getTodos());
        return "todo/my-todos";
    }

}
