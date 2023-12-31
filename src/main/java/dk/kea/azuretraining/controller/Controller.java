package dk.kea.azuretraining.controller;

import dk.kea.azuretraining.model.Employee;
import dk.kea.azuretraining.model.User;
import dk.kea.azuretraining.repositories.EmployeeRepository;
import dk.kea.azuretraining.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Employee> employees;
        if (name != null) {
            // Perform a search if the 'name' parameter is provided
            employees = employeeRepository.findEmployeeByName(name);
        } else {
            // Load all employees if no search parameter is provided
            employees = employeeRepository.findAll();
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("employees", employees);
        model.addAttribute("name", name); // Add the search query back to the form

        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "name") String name) {
        // Perform a search by name and return the results
        List<Employee> employees = employeeRepository.findEmployeeByName(name);
        model.addAttribute("employees", employees);
        model.addAttribute("name", name); // Add the search query back to the form

        return "index"; // Return the same view as the index
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user) {
        System.out.println(user);
        return userRepository.save(user);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> putEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employee.setId(id);
            employeeRepository.save(employee);
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> putUser(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user.setUserID(id);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee with id " + id + " deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with id " + id + " not found");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User with id " + id + " deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
        }
    }
}
