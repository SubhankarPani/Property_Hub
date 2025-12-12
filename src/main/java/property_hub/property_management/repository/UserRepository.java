package property_hub.property_management.repository;

import org.springframework.data.repository.CrudRepository;
import property_hub.property_management.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email); // Custom query to find user by email
}
