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

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
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

    // SHOW ALL PROJECTS OF ONE USER
    @RequestMapping(path = "/projectsof")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> showProjectsOfUser(@RequestParam String firstName, @RequestParam String lastName) {
        LOG.info("GET called on /projectsof resource");
        return userRepository.findProjectsByFirstNameAndLastName(firstName, lastName);
    }

    // CREATE A SAMPLE PROJECT
    @RequestMapping(path = "/sampleproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject() {
        // EXAMPLE Project
        Project project = new Project("Ball Werfen", "10.05.2018", 10, 20, 2, 2, 1, "www.google.com", new ArrayList<>());
        projectRepository.save(project);

        LOG.info(project.toString() + " successfully saved into DB");

        return project.getProject_id();
    }

    // CREATE A SAMPLE USER
    @RequestMapping(path = "/sampleuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser() {
        // EXAMPLE USER
        Date registerDate = new Date();
        String registerDateString = format.format(registerDate);
        Doctor doctor = new Doctor("Eich", "Route", 1, "Alabastia", "39829",
                "555-6891");
        Contact contact = new Contact("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        Project project1 = new Project("Ball werfen", registerDateString, 10, 20, 3, 3, 1,"www.google.com", new ArrayList<>());
        FoodLimit limit1 = new FoodLimit("Laktoseintoleranz", "");
        Illness limit2 = new Illness("Was ganz dolle schlimmes", "Macht immer richtig komische Sachen", "Honigmelone");
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        List<Limitation> limits = new ArrayList<>();
        limits.add(limit1);
        limits.add(limit2);
        User user = new User("Gary", "Eich", "10.01.1999", registerDateString, "Route 1",
                "Neuborkia",
                "96826", "555-5262", "437647298", false, contact,
                true, true, true, true, doctor,
                projects, limits, null);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }


    // ADD NEW USER
    @RequestMapping(path = "/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthDate,
                    @RequestParam String street, @RequestParam String city, @RequestParam String postcode,
                    @RequestParam String telephone, @RequestParam String healthcareNr, @RequestParam boolean allowTreatment,
                    @RequestParam Contact contact, @RequestParam boolean allowHomeAlone, @RequestParam boolean allowRiding,
                    @RequestParam boolean allowSwimming, @RequestParam boolean hasPayed, @RequestParam Doctor doctor, @RequestParam List<Project> projects,
                    @RequestParam List<Limitation> limits) {

        Date registerDate = new Date();
        String registerDateString = format.format(registerDate);
        User user = new User(firstName, lastName, birthDate, registerDateString, street, city,
                postcode, telephone, healthcareNr, allowTreatment, contact,
                allowHomeAlone, allowRiding, allowSwimming, hasPayed, doctor, projects, limits, null);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    // UPDATE USER INFORMATION
    @RequestMapping(path = "/updateuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long updateUser(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthDate,
                    @RequestParam String street, @RequestParam String city, @RequestParam String postcode,
                    @RequestParam String telephone, @RequestParam String healthcareNr, @RequestParam boolean allowTreatment,
                    @RequestParam Contact contact, @RequestParam boolean allowHomeAlone, @RequestParam boolean allowRiding,
                    @RequestParam boolean allowSwimming, @RequestParam boolean hasPayed, @RequestParam Doctor doctor) {

        User updatedUser = userRepository.findOne(id);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName((lastName));
        updatedUser.setBirthDate(birthDate);
        updatedUser.setPostcode(postcode);
        updatedUser.setStreet(street);
        updatedUser.setCity(city);
        updatedUser.setTelephone(telephone);
        updatedUser.setHealthcareNr(healthcareNr);
        updatedUser.setAllowTreatment(allowTreatment);
        updatedUser.setEmergencyContact(contact);
        updatedUser.setAllowHomeAlone(allowHomeAlone);
        updatedUser.setAllowRiding(allowRiding);
        updatedUser.setAllowSwimming(allowSwimming);
        updatedUser.setHasPayed(hasPayed);
        updatedUser.setDoctor(doctor);

        userRepository.save(updatedUser);

        LOG.info(updatedUser.toString() + " successfully saved into DB");

        return updatedUser.getId();
    }

    // GET USER INFORMATION BY ID
    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    User getUserById(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }

    // GET PROJECT INFORMATION BY ID
    @GetMapping(path = "/project/{project_id}")
    public @ResponseBody
    Project getProjectById(@PathVariable("project_id") Long project_id) {
        return projectRepository.findOne(project_id);
    }

    // CREATE NEW PROJECT
    @RequestMapping(path = "/createproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject(@RequestParam String name, @RequestParam String date, @RequestParam int age, @RequestParam int price, @RequestParam int slots,
                       @RequestParam int slotsFree, @RequestParam int slotsReserved, @RequestParam String weblink) {
        Project project = new Project(name, date, age, price, slots, slotsFree , slotsReserved, weblink, new ArrayList<>());
        projectRepository.save(project);
        LOG.info(project.toString() + "successfully saved into DB");

        return project.getProject_id();
    }

    // UPDATE PROJECT INFORMATION
    @RequestMapping(path = "/updateProject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long updateProject(@RequestParam long id, @RequestParam String name, @RequestParam String date, @RequestParam int age, @RequestParam int price, @RequestParam int slots,
                       @RequestParam int slotsReserved, @RequestParam String weblink) {
        Project updatedProject = projectRepository.findOne(id);
        updatedProject.setName(name);
        updatedProject.setDate(date);
        updatedProject.setAge(age);
        updatedProject.setPrice(price);
        updatedProject.setSlots(slots);
        updatedProject.setSlotsReserved(slotsReserved);
        updatedProject.setWeblink(weblink);
        projectRepository.save(updatedProject);
        LOG.info(updatedProject.toString() + "successfully updated/saved into DB");

        return updatedProject.getProject_id();
    }

    // DELETE PROJECT
    @RequestMapping(path = "/deleteproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String deleteProject(@RequestParam Long project_id) {

        Project theproj = projectRepository.findOne(project_id);
        projectRepository.delete(project_id);
        LOG.info(theproj.toString() + "deleted from DB");

        return theproj.toString();
    }

    // GET ALL PROJECT OF ONE USER
    @RequestMapping(path = "/projectsofuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> getProjectsOfUser(@RequestParam long id) {

        User user = userRepository.findOne(id);
        LOG.info("Returned all Projects of: " + user.toString());
        return user.getProjects();
    }

    // ADD NEW CANCELLATION
    @RequestMapping(path = "/addcancellation")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> addProjectToCancelled(@RequestParam long id, @RequestParam Project cancelledProject) {

        User user = userRepository.findOne(id);
        user.getCancellations().add(cancelledProject);
        LOG.info("Added Projects to List of Cancellations of: " + user.toString());
        return user.getCancellations();
    }


}
