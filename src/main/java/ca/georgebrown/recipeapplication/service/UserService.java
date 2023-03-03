package ca.georgebrown.recipeapplication.service;

import ca.georgebrown.recipeapplication.model.Recipe;
import ca.georgebrown.recipeapplication.model.User;

import java.util.List;


public interface UserService {

	 void save(User user);
	
	 User findByEmail(String email);
	
	 List<User> getAllUsers();
	
	 User findUserByUserId(Integer userId);

	 void addNewRecipeToUser(Recipe recipe);

	 void addFavoriteRecipe(Integer recipeId);

	 void removeFromFavorites(Integer recipeId);

	 User getCurrentUser();

}
