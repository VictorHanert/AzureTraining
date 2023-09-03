package dk.kea.azuretraining.useremployee.config;

import dk.kea.azuretraining.useremployee.model.Employee;
import dk.kea.azuretraining.useremployee.model.Gender;
import dk.kea.azuretraining.useremployee.model.User;
import dk.kea.azuretraining.useremployee.repositories.EmployeeRepository;
import dk.kea.azuretraining.useremployee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User();
        u1.setEmail("bjarne@mail.com");
        u1.setPassword("1234");
        userRepository.save(u1);

        u1.setUserID(0);
        u1.setEmail("lisa@hotmail.dk");
        u1.setPassword("password");
        userRepository.save(u1);

        User u2 = new User();
        u2.setEmail("morten@mail.com");
        u2.setPassword("1234");
        userRepository.save(u2);

        Employee emp1 = new Employee();
        emp1.setBorn(LocalDateTime.of(1990, 5, 10, 16, 10, 12));
        emp1.setName("Morten");
        emp1.setGender(Gender.MALE);
        emp1.setVegetarian(true);
        emp1.setUser(u2);
        employeeRepository.save(emp1);


    }
}
