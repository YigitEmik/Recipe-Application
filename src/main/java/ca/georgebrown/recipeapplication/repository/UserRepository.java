package ca.georgebrown.recipeapplication.repository;

import ca.georgebrown.recipeapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);
}

