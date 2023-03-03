package ca.georgebrown.recipeapplication.service.impl;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.model.Todo;
import ca.georgebrown.recipeapplication.model.User;
import ca.georgebrown.recipeapplication.repository.RecipeRepository;
import ca.georgebrown.recipeapplication.service.RecipeService;
import ca.georgebrown.recipeapplication.service.TodoService;
import ca.georgebrown.recipeapplication.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final TodoService todoService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserService userService, TodoService todoService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.todoService = todoService;
    }


    @Override
    public Recipe getRecipeById(Integer id) {
        return recipeRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Recipe> findPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("creationDate").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return recipeRepository.findAll(pageable);
    }

    @Override
    public List<Recipe> searchRecipeByNameOrIngredients(String search) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrIngredientsContainingIgnoreCaseOrderByCreationDateDesc(search, search);
    }

    @Override
    public void deleteRecipeById(Integer id) {
        User user = userService.getCurrentUser();

        List<Recipe> recipes = user.getRecipes();

        if (Objects.nonNull(recipes)) {
            recipes = recipes.stream().filter(x -> !id.equals(x.getId())).collect(Collectors.toList());
            user.setRecipes(recipes);
            userService.save(user);
        }

        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        List<Todo> todos = todoService.getTodosByRecipe(recipe);

        if (!CollectionUtils.isEmpty(todos)) {
            todos.forEach(x -> {
                x.setRecipe(null);
                todoService.saveTodos(x);
            });
        }

        recipe.setFavoriteUsers(null);
        recipeRepository.delete(recipe);
        todoService.deleteTodoList(todos);
    }
}
