package de.jonashackt.springbootvuejs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.ProjektRepository;
import de.jonashackt.springbootvuejs.repository.TeilnehmerRepository;
import de.jonashackt.springbootvuejs.transformation.AnmeldungJson;
import de.jonashackt.springbootvuejs.transformation.AnmeldungToAdmin;
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
import static java.lang.Math.toIntExact;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);
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
        LOG.info("Assigning project with id: " + projekt_id + " for user id: " + user_id);
        Teilnehmer teilnehmer = teilnehmerRepository.findOne(user_id);
        if (teilnehmer == null)
            return false;
        Projekt projekt = projektRepository.findOne(projekt_id);
        if (projekt == null)
            return false;

        List<Teilnehmer> registrierteTeilnehmer = projekt.getAnmeldungen();
        if(projekt.getAnmeldungen().size() >= projekt.getSlotsGesamt() - projekt.getSlotsReserviert()) {
            LOG.info("Could not assign " + teilnehmer.getNachname() + " to project " + projekt.getName() + " because all free slots are taken.");
            return false;
        }
        if(!registrierteTeilnehmer.contains(teilnehmer))
            registrierteTeilnehmer.add(teilnehmer);
        projekt.setAnmeldungen(registrierteTeilnehmer);
        projektRepository.save(projekt);

        LOG.info("Successfully assigned project " + projekt.getName() + " to user " + teilnehmer.getNachname());

        return true;
    }


    //Delete an item from a list of a user (e.g., an illness or so)
    @RequestMapping(path="/deletelistitem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean deleteItemFromUserList(@RequestBody Map<String, Long> delete_information) {
        Long user_id = delete_information.get("user_id");
        ListType typeOfList = ListType.values()[toIntExact(delete_information.get("type"))];
        int itemPosition = toIntExact(delete_information.get("item"));
        LOG.info("user_id: " + user_id + " type: " + typeOfList + " itemPosition: " + itemPosition);
        Teilnehmer teilnehmer = teilnehmerRepository.findOne(user_id);
        if (teilnehmer == null) {
            LOG.info("Failed to find user with id " + user_id);
            return false;
        }
        switch (typeOfList) {
            case krankheiten:
                if (teilnehmer.getKrankheiten().size() <= itemPosition){
                LOG.info("Position of item to delete exceeds list size for position " + itemPosition);
                return false;
                }
                teilnehmer.getKrankheiten().remove(itemPosition);
                teilnehmerRepository.save(teilnehmer);
                LOG.info("Successfully removed an item from list of illness");
                return  true;
            case essenslimitierungen:
                if (teilnehmer.getEssenLimitierungen().size() <= itemPosition){
                LOG.info("Position of item to delete exceeds list size for position " + itemPosition);
                return false;
                }
                teilnehmer.getEssenLimitierungen().remove(itemPosition);
                teilnehmerRepository.save(teilnehmer);
                LOG.info("Successfully removed item from list of food limitations");
                return  true;
            case allergien:
                if (teilnehmer.getAllergien().size() <= itemPosition){
                    LOG.info("Error: " + ListType.allergien + ": " + teilnehmer.getAllergien().size()+ ">=" + itemPosition);
                    return false;
                }
                teilnehmer.getAllergien().remove(itemPosition);
                teilnehmerRepository.save(teilnehmer);
                LOG.info("Successfully removed item from list of allergies");
                return  true;
            case medikamente:
                if (teilnehmer.getMedikamente().size() <= itemPosition) {
                    LOG.info("Position of item to delete exceeds list size for position " + itemPosition);
                    return false;
                }
                teilnehmer.getMedikamente().remove(itemPosition);
                teilnehmerRepository.save(teilnehmer);
                LOG.info("Successfully removed item from list of drug limitations");
                return true;
            case hitzeempfindlichkeit:
                if (teilnehmer.getHitzeempfindlichkeiten().size() <= itemPosition) {
                    LOG.info("Position of item to delete exceeds list size for position " + itemPosition);
                    return false;
                }
                teilnehmer.getHitzeempfindlichkeiten().remove(itemPosition);
                teilnehmerRepository.save(teilnehmer);
                LOG.info("Successfully removed item from list of heat problems");
                return true;
            default:
                LOG.info("Failed to find an according list type for the given id " + delete_information.get("item"));
                return false;
        }
    }

    /*******************************************
     * API for registering from Ferienpass-Anmeldung Microservice
     ******************************************/

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Long registerNewTeilnehmer(@RequestBody AnmeldungJson anmeldungJson) throws JsonProcessingException {

        LOG.info("New POST request from Ferienpass-Anmeldung Microservice containing new Teilnehmer");
        ObjectMapper mapper = new ObjectMapper();
        LOG.info("The anmeldungJson looks like: " + mapper.writeValueAsString(anmeldungJson));

        Teilnehmer neuAngemeldeterTeilnehmer = AnmeldungToAdmin.mapAnmeldedataToTeilnehmer(anmeldungJson, projektRepository.findAllProjects());

        Teilnehmer savedTeilnehmer = teilnehmerRepository.save(neuAngemeldeterTeilnehmer);

        LOG.info("Successfully saved new Teilnehmer " + neuAngemeldeterTeilnehmer.toString() + " into Admin-Backend-DB");

        return savedTeilnehmer.getId();
    }

    /*******************************************
     * API for projects (Projekte) functionality
     ******************************************/
    //Retrieve all projects
    @RequestMapping(path = "/allprojects", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Projekt> showAllProjects() {
        LOG.info("GET called on /allprojects resource");
        return projektRepository.findAllProjects();
    }

    // Retrieve all projects for a user's first and last name
    @RequestMapping(path = "/projectsof")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Projekt> showProjectsOfUser(@RequestParam String vorname, @RequestParam String nachname) {
        LOG.info("GET called on /projectsof resource");
        List<Projekt> resultList = new ArrayList<>();
        for (Projekt p:projektRepository.findAll()) {
            for (Teilnehmer t: p.getAnmeldungen()) {
                if(t.getVorname().equals(vorname) && t.getNachname().equals(nachname))
                    resultList.add(p);
            }
        }
        return resultList;
    }

    // Retrieve all projects for a user's ID
    @RequestMapping(path = "/projectsofid")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Projekt> showProjectsOfUserById(@RequestParam Long userID) {
        LOG.info("GET called on /projectsofid resource with userID: " + userID);
        List<Projekt> resultList = new ArrayList<>();
        for (Projekt p:projektRepository.findAll()) {
            for (Teilnehmer t: p.getAnmeldungen()) {
                if(t.getId() == userID)
                    resultList.add(p);
            }
        }
        LOG.info("Returning list with size of " + resultList.size());
        return resultList;
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

    //Get all users (Teilnehmer) for a given project by ID
    @GetMapping(path = "/projectRegistrations/{projekt_id}")
    public @ResponseBody
    List<Teilnehmer> getRegisteredUsersByProjectId(@PathVariable("projekt_id") Long projekt_id) {
        Projekt projekt = projektRepository.findOne(projekt_id);
        if (projekt == null) {
            LOG.info("Did not found project for id: " + projekt_id);
            return null;
        }
        LOG.info("Returning " + projekt.getAnmeldungen().size() + " registered participants for project " + projekt.getName());
        return projekt.getAnmeldungen();
    }

    //UPDATE USER
    @RequestMapping(path = "/updateUser")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Teilnehmer updateUser(@RequestParam Long userId, @RequestParam String vorname, @RequestParam String nachname,
                          @RequestParam String geburtsdatum, @RequestParam String strasse, @RequestParam String plz,
                          @RequestParam String stadt, @RequestParam String tel, @RequestParam String krankenkasse,
                          @RequestParam String kontaktName, @RequestParam String kontaktAdresse,
                          @RequestParam String kontaktTel, @RequestParam String arztName, @RequestParam String arztAdresse,
                          @RequestParam String arztTel, @RequestParam Boolean erlaubeMedikamentation, @RequestParam Boolean darfSchwimmen,
                          @RequestParam Boolean darfReiten, @RequestParam Boolean darfAlleinNachHause, @RequestParam String schwimmAbzeichen,
                          @RequestParam Boolean bezahlt, @RequestParam Boolean darfBehandeltWerden, @RequestParam Boolean liegtBehinderungVor,
                          @RequestParam Boolean behinderungG, @RequestParam Boolean behinderungH, @RequestParam Boolean behinderungAG,
                          @RequestParam Boolean behinderungB1, @RequestParam Boolean behinderungG1, @RequestParam Boolean behinderungB,
                          @RequestParam Boolean behinderungTBL, @RequestParam Boolean rollstuhl,
                          @RequestParam String behinderungHilfsmittel, @RequestParam Boolean wertMarke, @RequestParam Boolean begleitungNotwending,
                          @RequestParam Boolean begleitPflege, @RequestParam Boolean begleitMedVor, @RequestParam Boolean begleitMobilität,
                          @RequestParam Boolean begleitOrientierung, @RequestParam Boolean begleitSozial, @RequestParam String eingeschränkteSinne,
                          @RequestParam String hinweiseZumUmgang, @RequestParam Boolean behinderungUnterstützung,
                          @RequestParam String untersützungKontakt, @RequestParam Boolean kostenÜbernahme) {
        Teilnehmer teilnehmer = teilnehmerRepository.findOne(userId);
        if (teilnehmer == null) {
            LOG.info("Could not update user with id: " + userId + " because there is no entry in the database.");
            return null;
        }
        //Handicap
        teilnehmer.setLiegtBehinderungVor(liegtBehinderungVor);
        if (liegtBehinderungVor) {
            Behinderung b = teilnehmer.getBehinderung();
            b.setMerkzeichen_ErheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G(behinderungG);
            b.setMerkzeichen_Taubblind_TBL(behinderungTBL);
            b.setMerkzeichen_AussergewoehnlicheGehbehinderung_aG(behinderungAG);
            b.setMerkzeichen_BerechtigtZurMitnahmeEinerBegleitperson_B(behinderungB);
            b.setMerkzeichen_Blind_Bl(behinderungB1);
            b.setMerkzeichen_Hilflosigkeit_H(behinderungH);
            b.setRollstuhlNutzungNotwendig(rollstuhl);
            b.setMerkzeichen_Gehoerlos_Gl(behinderungG1);
            b.setHinweiseZumUmgangMitDemKind(hinweiseZumUmgang);
            b.setBeantragungKostenuebernahmeBegleitpersonNotwendig(kostenÜbernahme);
            b.setBegleitpersonMedizinischeVersorgung(begleitMedVor);
            b.setBegleitpersonMobilitaet(begleitMobilität);
            b.setBegleitpersonSozialeBegleitung(begleitSozial);
            b.setBegleitpersonOrientierung(begleitOrientierung);
            b.setBegleitpersonPflege(begleitPflege);
            b.setBegleitungNotwendig(begleitungNotwending);
            b.setEingeschraenkteSinne(eingeschränkteSinne);
            b.setUnterstuetzungSucheBegleitpersonNotwendig(behinderungUnterstützung);
            b.setGewohnterBegleitpersonenDienstleister(untersützungKontakt);
            b.setWertmarkeVorhanden(wertMarke);
            b.setWeitereHilfsmittel(behinderungHilfsmittel);
        }

        //Basic data
        teilnehmer.setVorname(vorname);
        teilnehmer.setNachname(nachname);
        String[] dateRaw = geburtsdatum.split(",");
        teilnehmer.setGeburtsdatum(LocalDate.of(Integer.valueOf(dateRaw[0]), Integer.valueOf(dateRaw[1]), Integer.valueOf(dateRaw[2])));
        teilnehmer.setStrasse(strasse);
        teilnehmer.setPostleitzahl(plz);
        teilnehmer.setStadt(stadt);
        teilnehmer.setTelefon(tel);
        teilnehmer.setKrankenkasse(krankenkasse);

        //Emergency contact
        Kontakt kontakt = teilnehmer.getNotfallKontakt();
        kontakt.setAddress(kontaktAdresse);
        kontakt.setName(kontaktName);
        kontakt.setTelephone(kontaktTel);
        teilnehmer.setNotfallKontakt(kontakt);

        //Medical contact
        Arzt arzt = teilnehmer.getArzt();
        arzt.setAddress(arztAdresse);
        arzt.setName(arztName);
        arzt.setTelephone(arztTel);
        teilnehmer.setArzt(arzt);

        //Diverse
        teilnehmer.setDarfBehandeltWerden(darfBehandeltWerden);
        teilnehmer.setDarfAlleinNachHause(darfAlleinNachHause);
        teilnehmer.setDarfReiten(darfReiten);
        teilnehmer.setDarfSchwimmen(darfSchwimmen);
        teilnehmer.setSchwimmAbzeichen(schwimmAbzeichen);
        teilnehmer.setErlaubeMedikamentation(erlaubeMedikamentation);
        teilnehmer.setBezahlt(bezahlt);




        teilnehmerRepository.save(teilnehmer);
        LOG.info("Successfully updated Teilnehmer " + nachname);
        return teilnehmer;
    }


}
