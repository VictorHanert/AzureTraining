package dk.kea.azuretraining.repositories;

import dk.kea.azuretraining.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeeByName(String name);
}
