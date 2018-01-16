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
import java.text.SimpleDateFormat;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/allusers")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<User> showAllUsers() {
        LOG.info("GET called on /allusers resource");
        return userRepository.findAllUsers();
    }

    @RequestMapping(path = "/allprojects")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> showAllProjects() {
        LOG.info("GET called on /allprojects resource");
        return projectRepository.findAllProjects();
    }

    @RequestMapping(path = "/projectsof")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> showProjectsOfUser(@RequestParam String firstName, @RequestParam String lastName) {
        LOG.info("GET called on /projectsof resource");
        return userRepository.findProjectsByFirstNameAndLastName(firstName,lastName);
    }

    @RequestMapping(path = "/sampleproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject () {
        // EXAMPLE Project
        Project project = new Project("Ball Werfen", new Date(), 10, 20,2,1,"www.google.com",new ArrayList<>());
        projectRepository.save(project);

        LOG.info(project.toString() + " successfully saved into DB");

        return project.getProject_id();
    }

    @RequestMapping(path = "/sampleuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser () {
        // EXAMPLE USER
        Date registerDate = new Date();
        Date birthDate = new Date();
        Doctor doctor = new Doctor("Eich", "Route", 1, "Alabastia", "39829",
                "555-6891");
        Contact contact = new Contact("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        Project project1 = new Project("Ball werfen", registerDate, 10, 20, 3, 1, "www.google.com", new ArrayList<>());
        FoodLimit limit1 = new FoodLimit("Laktoseintoleranz","");
        Illness limit2 = new Illness("Was ganz dolle schlimmes", "Macht immer richtig komische Sachen","Honigmelone");
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        List<Limitation> limits = new ArrayList<>();
        limits.add(limit1);
        limits.add(limit2);
        User user = new User("Gary", "Eich", birthDate, registerDate, "Route 1",
                "Neuborkia",
                "96826", "555-5262", "437647298", false,  contact,
                true, true, true, true, doctor,
                projects, limits, null);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }



    @RequestMapping(path = "/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser (@RequestParam String firstName, @RequestParam String lastName, @RequestParam Date birthDate,
                     @RequestParam String street, @RequestParam String city, @RequestParam String postcode,
                     @RequestParam String telephone, @RequestParam String healthcareNr, @RequestParam boolean allowTreatment,
                     @RequestParam Contact contact, @RequestParam boolean allowHomeAlone, @RequestParam boolean allowRiding,
                     @RequestParam boolean allowSwimming, @RequestParam boolean hasPayed, @RequestParam Doctor doctor, @RequestParam List<Project> projects,
                     @RequestParam List<Limitation> limits) {

        Date registerDate = new Date();
        User user = new User(firstName, lastName, birthDate, registerDate, street, city,
                postcode, telephone, healthcareNr, allowTreatment,  contact,
                allowHomeAlone, allowRiding, allowSwimming, hasPayed, doctor, projects, limits, null);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }

}
