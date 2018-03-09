package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain_refactored.*;
import de.jonashackt.springbootvuejs.repository_refactored.ProjektRepository;
import de.jonashackt.springbootvuejs.repository_refactored.TeilnehmerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class BackendController_refactored {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController_refactored.class);
    @Autowired
    private TeilnehmerRepository teilnehmerRepository;
    @Autowired
    private ProjektRepository projektRepository;


    /*************************************
     * General API
     *********************************/

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return "hello";
    }

    /*******************************************
     * API for user (Teilnehmer) functionality
     ******************************************/

    //Retrieve all users in the data base
    @RequestMapping(path = "/allusers")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Teilnehmer> showAllUsers() {
        LOG.info("GET called on /allusers resource");
        return teilnehmerRepository.findAllUsers();
    }
    // GET USER INFORMATION BY ID
    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    Teilnehmer getUserById(@PathVariable("id") long id) {
        return teilnehmerRepository.findOne(id);
    }

    // CREATE A SAMPLE USER
    @RequestMapping(path = "/sampleuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser() {
        // EXAMPLE USER
        LocalDate registerDate = LocalDate.now();
        Arzt arzt = new Arzt("Eich", "Route 1 Alabastia, 39829",
                "555-6891");
        Kontakt kontact = new Kontakt("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        Projekt project1 = new Projekt("Ball werfen", registerDate, 10, 20, 3, 1,"www.google.com", new ArrayList<>());
        EssenLimitierung laktose = new EssenLimitierung("Laktoseintoleranz", "");
        Krankheit krank = new Krankheit("Grippe", "Muss oft Husten", "Hustenbonbons");
        List<Projekt> projects = new ArrayList<Projekt>();
        projects.add(project1);
        projektRepository.save(project1);

        List<EssenLimitierung> essenLimitierungen = new ArrayList<EssenLimitierung>();
        essenLimitierungen.add(laktose);
        List<Krankheit> krankheiten = new ArrayList<Krankheit>();
        krankheiten.add(krank);
        List<Allergie> allergien = new ArrayList<Allergie>();
        allergien.add(new Allergie("Heuschnupfen","Nasenspray","nur 2x am Tag"));
        List<Behinderung> behinderungen = new ArrayList<Behinderung>();
        behinderungen.add(new Behinderung("Gehörlos",new BehinderungKodierung("G1"),false,true,true, true));
        Teilnehmer user = new Teilnehmer("Gary","Eich", LocalDate.of(2005,10,20),registerDate, "Bahnhofstraße 4", "Weimar", "99423", "03544444", "0453434", true, kontact,
                true, false, false, false, arzt, projects, allergien, essenLimitierungen, krankheiten, behinderungen,new ArrayList<Projekt>());

        teilnehmerRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    //Add a new user (Teilnehmer) based on a user object
    @RequestMapping(path = "/adduser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser(@RequestBody Teilnehmer user) {

        teilnehmerRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }


    //Assign Project to user
    @RequestMapping(path = "/assignProject", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean assignProjectToUser(@RequestBody Map<String, Long> ids) {
        Long user_id = ids.get("user");
        Long projekt_id = ids.get("project");
        Teilnehmer t = teilnehmerRepository.findOne(user_id);
        if (t == null)
            return false;
        List<Projekt> currentProjects = t.getAngemeldeteProjekte();
        Projekt p = projektRepository.findOne(projekt_id);
        if (p == null)
            return false;
        currentProjects.add(p);
        t.setAngemeldeteProjekte(currentProjects);
        teilnehmerRepository.save(t);

        LOG.info("Successfully assigned project " + p.toString() + " to user " + t.toString());

        return true;
    }

    /*******************************************
     * API for projects (Projekte) functionality
     ******************************************/
    //Retrieve all projects
    @RequestMapping(path = "/allprojects")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Projekt> showAllProjects() {
        LOG.info("GET called on /allprojects resource");
        return projektRepository.findAllProjects();
    }

    // Retrieve all projects for a user's first and last name
    @RequestMapping(path = "/projectsof")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Projekt> showProjectsOfUser(@RequestParam String vorname, @RequestParam String nachname) {
        LOG.info("GET called on /projectsof resource");
        return teilnehmerRepository.findProjektsByVornameAndNachname(vorname, nachname);
    }

    // CREATE NEW PROJECT
    @RequestMapping(path = "/createproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject(@RequestParam String name, @RequestParam String date, @RequestParam int age, @RequestParam int price, @RequestParam int slots,
                       @RequestParam int slotsReserved, @RequestParam String weblink) {
        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Projekt project = new Projekt(name, d, age, price, slots, slotsReserved, weblink, new ArrayList<>());
        projektRepository.save(project);
        LOG.info(project.toString() + "successfully saved into DB");

        return project.getId();
    }

    // DELETE PROJECT
    @RequestMapping(path = "/deleteproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Boolean deleteProject(@RequestParam Long project_id) {

        Projekt p = projektRepository.findOne(project_id);
        if (p == null)
            return  false;
        p.setAktiv(false);
        projektRepository.save(p);
        LOG.info(p.toString() + " is set to inactive");

        return true;
    }

    // CREATE A SAMPLE PROJECT
    @RequestMapping(path = "/sampleproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject() {
        // EXAMPLE Project
        LocalDate localDate = LocalDate.of(2018, 5, 10);
        Projekt project = new Projekt("Ball Werfen", LocalDate.of(2018, 5, 10), 10, 20, 2, 1, "www.google.com", new ArrayList<>());
        projektRepository.save(project);

        LOG.info(project.toString() + " successfully saved into DB");

        return project.getId();
    }

    //Add a new user (Teilnehmer) based on a user object
    @RequestMapping(path = "/addproject", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject(@RequestBody Projekt p) {

        projektRepository.save(p);

        LOG.info(p.toString() + " successfully saved into DB");

        return p.getId();
    }


    // GET PROJECT INFORMATION BY ID
    @GetMapping(path = "/project/{projekt_id}")
    public @ResponseBody
    Projekt getProjectById(@PathVariable("projekt_id") Long projekt_id) {
        return projektRepository.findOne(projekt_id);
    }

}
