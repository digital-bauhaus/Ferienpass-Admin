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

    //INITIAL REQUEST FOR FORM OBJECT
    @RequestMapping(path = "/form")
    public @ResponseBody
    /*Form!*/String sendinitialForm() {

        //!!!
        //Return Type of function is <String> only for debugging, final return type is a Form object

        //Section List of Form object
        List<Section> sections= new ArrayList<>();

        //Building structure of form-data.json
        Form form = new Form("Ferienpass Weimar – Anmeldung",sections);
        //frage: wovon wird id bestimmt?
        form.setForm_id(1);

        //Form Object holds 7 sections
        //SECTION 1 GRUNDDATEN
        //All Params of every TextField
        Param textfield1 = new Param("Familienname",true);
        Param textfield2 = new Param("Vorname meines Kindes",true);
        Param textfield3 = new Param("Geburtsdatum meines Kindes",true);
        Param textfield4 = new Param("Straße",true);
        Param textfield5 = new Param("Wohnort",true);
        Param textfield6 = new Param("Telefon",true,"tel");

        //Param Lists of every Components(TextFields) in Grunddaten
        List<Param> params_component_param1 = new ArrayList<>();
        List<Param> params_component_param2 = new ArrayList<>();
        List<Param> params_component_param3 = new ArrayList<>();
        List<Param> params_component_param4 = new ArrayList<>();
        List<Param> params_component_param5 = new ArrayList<>();
        List<Param> params_component_param6 = new ArrayList<>();

        //adding all parameter to List of components of Grunddaten
        params_component_param1.add(textfield1);
        params_component_param2.add(textfield2);
        params_component_param3.add(textfield3);
        params_component_param4.add(textfield4);
        params_component_param5.add(textfield5);
        params_component_param6.add(textfield6);

        //creating all Components in Grunddaten
        Component component_param1 = new Component("TextField", params_component_param1);
        Component component_param2 = new Component("TextField", params_component_param2);
        Component component_param3 = new Component("TextField", params_component_param3);
        Component component_param4 = new Component("TextField", params_component_param4);
        Component component_param5 = new Component("TextField", params_component_param5);
        Component component_param6 = new Component("TextField", params_component_param6);

        List<Component> components_param1 = new ArrayList<>();
    //adding all Components to Grunddaten Component List
        components_param1.add(component_param1);
        components_param1.add(component_param2);
        components_param1.add(component_param3);
        components_param1.add(component_param4);
        components_param1.add(component_param5);
        components_param1.add(component_param6);


        Param param1 = new Param("Grunddaten",true, components_param1);
        List<Param> params_s1 = new ArrayList<>();
        Component c_s1 = new Component("Group", params_s1);
        List<Component> components_sec1 = new ArrayList<>();
        components_sec1.add(c_s1);
        //Section 1
        Section s1_grunddaten = new Section("Grunddaten",components_sec1);
        sections.add(s1_grunddaten);


        //OTHER SECTIONS FOLLOWING

        /*Section s2_angebote = new Section("Angebote",components);
        Section s3_teilnehmer = new Section("Teilnehmer",components);
        Section s4_allerg_krank = new Section("Allergien, Krankheiten, …",components);
        Section s5_behinderung = new Section("Angaben bei Behinderung",components);
        Section s6_erklaerung = new Section("Erklärung",components);
        Section s7_datenschutz = new Section("Datenschutzerklärung",components);
        sections.add(s2_angebote);
        sections.add(s3_teilnehmer);
        sections.add(s4_allerg_krank);
        sections.add(s5_behinderung);
        sections.add(s6_erklaerung);
        sections.add(s7_datenschutz);*/
        //form.getSections();


        //only for testing if Form was createt
        long form_id_check = form.getForm_id();
        //output & return statement
        LOG.info("GET called on /form resource || For debugging purpose: Form ID= " + form_id_check);
        //will return the Form object holding the structure of form-data.json soon
        return "Called api/form... Will return a Form Object soon!";
    }

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
        Project project = new Project("Ball Werfen", "10.05.2018", 10, 20, 2, 1, "www.google.com", new ArrayList<>());
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
        Doctor doctor = new Doctor("Eich", "Route 1 Alabastia, 39829",
                "555-6891");
        Contact contact = new Contact("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        Project project1 = new Project("Ball werfen", registerDateString, 10, 20, 3, 1,"www.google.com", new ArrayList<>());
        FoodLimit limit1 = new FoodLimit("Laktoseintoleranz", "");
        Illness limit2 = new Illness("Was ganz dolle schlimmes", "Macht immer richtig komische Sachen", "Honigmelone");
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projectRepository.save(project1);
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
    @RequestMapping(path = "/adduser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser(@RequestBody User user) {

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
                    @RequestParam String contactName, @RequestParam String contactAddress, @RequestParam String contactTelephone,
                    @RequestParam boolean allowHomeAlone, @RequestParam boolean allowRiding,
                    @RequestParam boolean allowSwimming, @RequestParam boolean hasPayed, @RequestParam String doctorName,
                    @RequestParam String doctorAddress, @RequestParam String doctorTelephone) {

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
        updatedUser.getEmergencyContact().setName(contactName);
        updatedUser.getEmergencyContact().setAddress(contactAddress);
        updatedUser.getEmergencyContact().setTelephone(contactTelephone);
        updatedUser.setAllowHomeAlone(allowHomeAlone);
        updatedUser.setAllowRiding(allowRiding);
        updatedUser.setAllowSwimming(allowSwimming);
        updatedUser.setHasPayed(hasPayed);
        updatedUser.getDoctor().setName(doctorName);
        updatedUser.getDoctor().setAddress(doctorAddress);
        updatedUser.getDoctor().setTelephone(doctorTelephone);
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
                       @RequestParam int slotsReserved, @RequestParam String weblink) {
        Project project = new Project(name, date, age, price, slots, slotsReserved, weblink, new ArrayList<>());
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
        theproj.setAlive(false);
        projectRepository.save(theproj);
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

    // GET USERS OF PROJECT
    @RequestMapping(path = "/usersofproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<User> getUsersOfProject(@RequestParam long id) {

        Project project = projectRepository.findOne(id);
        LOG.info("Returned all Users of: " + project.toString());
        return project.getUsers();
    }

    // RESERVE SLOTS IN PROJECT
    @RequestMapping(path = "/reserveslotsinproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    int reserve(@RequestParam long id, @RequestParam int slots){
        Project project = projectRepository.findOne(id);
        int totalslots = project.getSlots();
        int reservedslots = project.getSlotsReserved();
        int allslots = reservedslots + slots;
        if (reservedslots == 0 && reservedslots <= totalslots)
        {
            project.setSlotsReserved(slots);
            LOG.info("Reserved " + slots + " Slots in " +project.toString());
            return project.getSlotsReserved();
        }
        else if (allslots <= totalslots)
        {
            project.setSlotsReserved(allslots);
            LOG.info("Reserved " + slots + " Slots in " +project.toString());
            return project.getSlotsReserved();
        }
        else {
            LOG.info("Error");
            return project.getSlotsReserved();
        }
    }



}
