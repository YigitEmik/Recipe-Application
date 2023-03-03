package ca.georgebrown.recipeapplication.controller;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.request.SearchRequest;
import ca.georgebrown.recipeapplication.service.RecipeService;
import ca.georgebrown.recipeapplication.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final UserService userService;
    private final RecipeService recipeService;

    public RecipeController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Integer id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipes/recipe-detail";
    }

    @GetMapping("/create")
    public String createRecipePage(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/create-recipes";
    }

    @PostMapping("/create")
    public String createRecipe(Recipe recipe) {
        userService.addNewRecipeToUser(recipe);
        return "redirect:/user/my-recipes";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo, Model model) {
        int pageSize = 10;
        Page<Recipe> page = recipeService.findPaginated(pageNo, pageSize);
        List<Recipe> listRecipe = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listRecipes", listRecipe);
        model.addAttribute("search", new SearchRequest());
        return "recipes/recipes-list";
    }


    @GetMapping("/add/favorite/{id}")
    public String addFavorite(@PathVariable Integer id) {
        userService.addFavoriteRecipe(id);
        return "redirect:/user/favorites";
    }

    @GetMapping("/remove/favorite/{id}")
    public String removeFavorite(@PathVariable Integer id) {
        userService.removeFromFavorites(id);
        return "redirect:/user/favorites";
    }

    @PostMapping("/search")
    public String searchRecipes(SearchRequest search, Model model) {
        model.addAttribute("listRecipes", recipeService.searchRecipeByNameOrIngredients(search.getQuery()));
        model.addAttribute("search", new SearchRequest());
        return "recipes/recipes-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Integer id) {
       recipeService.deleteRecipeById(id);
        return "redirect:/user/my-recipes";
    }
}
