package dk.kea.azuretraining.useremployee.repositories;

import dk.kea.azuretraining.useremployee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
