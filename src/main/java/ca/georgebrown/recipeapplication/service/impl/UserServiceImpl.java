package ca.georgebrown.recipeapplication.service.impl;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.model.User;
import ca.georgebrown.recipeapplication.repository.RecipeRepository;
import ca.georgebrown.recipeapplication.repository.UserRepository;
import ca.georgebrown.recipeapplication.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public UserServiceImpl(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUserId(Integer userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public void addNewRecipeToUser(Recipe recipe) {
        User user = getCurrentUser();
        List<Recipe> recipes = user.getRecipes();
        if (CollectionUtils.isEmpty(recipes)) {
            recipes = new ArrayList<>();
        }
        recipes.add(recipe);
        user.setRecipes(recipes);
        save(user);
    }

    @Override
    public void addFavoriteRecipe(Integer recipeId) {
        User user = getCurrentUser();
        List<Recipe> recipes = getFavoriteRecipesCurrentUser();
        if (recipes.stream().anyMatch(x -> recipeId.equals(x.getId()))) {
            return;
        }
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        recipes.add(recipe);
        user.setFavoriteRecipes(recipes);
        save(user);
    }

    @Override
    public void removeFromFavorites(Integer recipeId) {
        if (Objects.isNull(recipeId)) {
            return;
        }
        List<Recipe> recipes = getFavoriteRecipesCurrentUser();
        User user = getCurrentUser();
        recipes = recipes.stream().filter(x -> !recipeId.equals(x.getId())).collect(Collectors.toList());
        user.setFavoriteRecipes(recipes);
        save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findByEmail(email);
    }

    private List<Recipe> getFavoriteRecipesCurrentUser() {
        User user = getCurrentUser();
        return Objects.nonNull(user.getRecipes()) ? user.getFavoriteRecipes() : new ArrayList<>();
    }

}
