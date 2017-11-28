package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/all")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<User> showAllUsers() {
        LOG.info("GET called on /all resource");
        return userRepository.findAllUsers();
    }

    @RequestMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser (/*@RequestParam String firstName, @RequestParam String lastName*/) {
        Date registerDate = new Date();
        Date birthDate = new Date();
        Doctor doctor = new Doctor("Eich", "Route", 1, "Alabastia", "39829",
                "555-6891");
        Project project1 = new Project("Ball werfen", registerDate, 3, 1, "www.google.com");
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        List<Limitation> limits = new ArrayList<>();
        User user = new User("Gary", "Eich", birthDate, registerDate, "Route", 1,
                "Neuborkia",
                "96826", "555-5262", "437647298", "Peter August 11194819",
                true, true, true, doctor,
                projects, limits);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }

}
