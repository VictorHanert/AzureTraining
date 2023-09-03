package dk.kea.azuretraining.repositories;

import dk.kea.azuretraining.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
