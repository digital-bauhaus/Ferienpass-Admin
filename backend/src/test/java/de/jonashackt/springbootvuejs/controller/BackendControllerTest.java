package de.jonashackt.springbootvuejs.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.FerienpassAdminApplication;
import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.TeilnehmerRepositoryTest;
import de.jonashackt.springbootvuejs.transformation.AnmeldungJson;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = FerienpassAdminApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port = 8089"
)
public class BackendControllerTest {
    private static final String BASE_URL = "http://localhost:8089/api";

    @Value("classpath:requests/anmeldung-post-data.json")
    private Resource anmeldungJsonFile;
    private ObjectMapper objectMapper = new ObjectMapper();

    private TeilnehmerRepositoryTest teilnehmerRepositoryTest = new TeilnehmerRepositoryTest();

    /****************************
     * Test user (Teilnehmer) API
     ****************************/
    @Test
    public void addNewUserAndRetrieveItBack() {
        Teilnehmer user = teilnehmerRepositoryTest.createUser();

        Long userId = addUser(user);

        Teilnehmer responseUser = getUser(userId);
        assertThat(responseUser.getVorname(), is(user.getVorname()));
        assertThat(responseUser.getNachname(), is(user.getNachname()));
    }

    @Test
    public void addNewUserAddSeveralListItemsAndRemoveThemAgain() {
        Teilnehmer user = teilnehmerRepositoryTest.createUser();

        //add some allergies
        Allergie a1 = new Allergie("Arbeiten","Viele Aufgaben und viel reden");
        Allergie a2 = new Allergie("Freizeit","Urlaub und Spaß haben");
        user.setAllergien(new ArrayList<>());
        user.getAllergien().add(a1);
        user.getAllergien().add(a2);
        assertThat(user.getAllergien().size(), is(2));

        //add some food limitations
        EssenLimitierung e1 = new EssenLimitierung("Fleisch", "vegetarier");
        EssenLimitierung e2 = new EssenLimitierung("Obst", "Sollte dennoch Obst essen");
        user.setEssenLimitierungen(new ArrayList<>());
        user.getEssenLimitierungen().add(e1);
        user.getEssenLimitierungen().add(e2);
        assertThat(user.getEssenLimitierungen().size(), is(2));

        //add some illnesses
        Krankheit k1 = new Krankheit("Grippe", "Sollte viel Pausen machen", "Keine");
        Krankheit k2 = new Krankheit("Husten", "Immer in den Arm husten", "Hustensaft");
        user.setKrankheiten(new ArrayList<>());
        user.getKrankheiten().add(k1);
        user.getKrankheiten().add(k2);
        assertThat(user.getKrankheiten().size(), is(2));

        //add some drugs
        Medikament m1 = new Medikament("Nasentropfen","2x am Tag");
        Medikament m2 = new Medikament("Hustensaft", "nach dem Essen");
        user.setMedikamente(new ArrayList<>());
        user.getMedikamente().add(m1);
        user.getMedikamente().add(m2);
        assertThat(user.getMedikamente().size(),is(2));

        //add some heat problems
        Hitzeempfindlichkeit h1 = new Hitzeempfindlichkeit("heiss","ausschlag muss behandelt werden");
        user.setHitzeempfindlichkeiten(new ArrayList<>());
        user.getHitzeempfindlichkeiten().add(h1);
        assertThat(user.getHitzeempfindlichkeiten().size(),is(1));


        Long userId = addUser(user);

        Teilnehmer responseUser = getUser(userId);

        assertThat(responseUser.getAllergien().size(), is(2));
        assertThat(responseUser.getEssenLimitierungen().size(), is(2));
        assertThat(responseUser.getKrankheiten().size(), is(2));
        assertThat(responseUser.getHitzeempfindlichkeiten().size(),is(1));
        assertThat(responseUser.getMedikamente().size(),is(2));

        //Begin with test

        //Remove second allergy
        Map<String,Long> newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.allergien.ordinal()).longValue());
        newID_Map.put("item", new Long(1));
        Boolean success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/deletelistitem")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));

        responseUser = getUser(userId);
        assertThat(responseUser.getAllergien().size(),is(1));
        assertThat(responseUser.getAllergien().get(0).getName(),is(a1.getName()));

        //remove first food limitation
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.essenslimitierungen.ordinal()).longValue());
        newID_Map.put("item", new Long(0L));
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/deletelistitem")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));

        responseUser = getUser(userId);
        assertThat(responseUser.getEssenLimitierungen().size(),is(1));
        assertThat(responseUser.getEssenLimitierungen().get(0).getName(),is(e2.getName()));

        //remove second illness
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.krankheiten.ordinal()).longValue());
        newID_Map.put("item", new Long(1L));
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/deletelistitem")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));

        responseUser = getUser(userId);
        assertThat(responseUser.getKrankheiten().size(),is(1));
        assertThat(responseUser.getKrankheiten().get(0).getName(),is(k1.getName()));

        //Remove second drug
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.medikamente.ordinal()).longValue());
        newID_Map.put("item", new Long(1));
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/deletelistitem")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);

        assertThat(success,is(true));
        responseUser = getUser(userId);
        assertThat(responseUser.getMedikamente().size(),is(1));
        assertThat(responseUser.getMedikamente().get(0).getName(),is(m1.getName()));

        //Remove hitzeempfindlichkeit
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.hitzeempfindlichkeit.ordinal()).longValue());
        newID_Map.put("item", new Long(0));
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/deletelistitem")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);

        assertThat(success,is(true));
        responseUser = getUser(userId);
        assertThat(responseUser.getHitzeempfindlichkeiten().size(),is(0));

    }

    @Test
    public void addTwoUsersAndCheckWhetherAllUsersAreComplete() {
        int initialSize = getAllUsers().size();

        Long userId = addUser(teilnehmerRepositoryTest.createUser());
        Long userId2 = addUser(teilnehmerRepositoryTest.createUser());

        List<Teilnehmer> allUsers = getAllUsers();

        assertThat(allUsers.size()-initialSize, is(2));
        long id1 = allUsers.get(initialSize).getId();
        long id2 = allUsers.get(initialSize+1).getId();
        assertThat(id1,is(userId));
        assertThat(id2,is(userId2));

    }

    /*******************************************
     * Tests API for registering from Ferienpass-Anmeldung Microservice
     ******************************************/

    @Test
    public void addNewTeilnehmerFromFerienpassAnmeldungMicroservice() throws IOException {

        AnmeldungJson anmeldungJson = objectMapper.readValue(anmeldungJsonFile.getInputStream(), AnmeldungJson.class);

        Long userId = registerNewUserFromAnmeldungFrontend(anmeldungJson);

        Teilnehmer responseUser = getUser(userId);

        assertThat(responseUser.getVorname(), is("Paul"));
        assertThat(responseUser.getNachname(), is("Siegmund"));
        assertThat(responseUser.getGeburtsdatum(), is(LocalDate.of(2019,1,10)));
        assertThat(responseUser.getStrasse(), is("Rainer-Maria-Rilke-Strasse 33"));
        assertThat(responseUser.getPostleitzahl(), is("99423"));
        assertThat(responseUser.getStadt(), is("Weimar"));
        assertThat(responseUser.getTelefon(), is("03643 / 123456"));


        List<Projekt> allProjects = getAllProjects();

        allProjects.forEach(projekt -> System.out.println(projekt.getId() + ", Name: " + projekt.getName()));
        allProjects.forEach(projekt -> {
            projekt.getAnmeldungen().forEach(teilnehmer -> System.out.println(projekt.getId() + ", Teilnehmer: " + teilnehmer.getVorname() ));

        });
        System.out.println("Projekte " + allProjects);


        // Ist der User in Projekt 1 & 3 angemeldet?
//        List<Teilnehmer> anmeldungenProjekt1 = getProjekt(allProjects,"Ball Werfen").getAnmeldungen();
//        System.out.println("Projekt 1 contains User? " + containsTeilnehmer(responseUser, anmeldungenProjekt1));
//        System.out.println("Projekt 1 " + anmeldungenProjekt1);
//        assertThat(containsTeilnehmer(responseUser, anmeldungenProjekt1), is(true));

        List<Teilnehmer> anmeldungenProjekt2 = getProjekt(allProjects,"Bauspielplatz").getAnmeldungen();
        System.out.println("Projekt 2 contains User? " + containsTeilnehmer(responseUser, anmeldungenProjekt2));
        assertThat(containsTeilnehmer(responseUser, anmeldungenProjekt2), is(false));

        List<Teilnehmer> anmeldungenProjekt3 = getProjekt(allProjects,"Papier-Werkstatt").getAnmeldungen();
        System.out.println("Projekt 3 contains User? " + containsTeilnehmer(responseUser, anmeldungenProjekt3));
        assertThat(containsTeilnehmer(responseUser, anmeldungenProjekt3), is(true));


        List<Allergie> allergien = responseUser.getAllergien();
        assertThat(allergien.get(0).getName(), is("Heuschnupfen"));
        assertThat(allergien.get(1).getName(), is("Hausstaub"));
        assertThat(allergien.get(2).getName(), is("Nussallergie"));
        assertThat(allergien.get(3).getName(), is("Katzenhaarallergie"));
        assertThat(allergien.get(4).getName(), is("Regenallergie"));

        List<Krankheit> krankheiten = responseUser.getKrankheiten();
        assertThat(krankheiten.get(0).getName(), is("Epilepsie"));
        assertThat(krankheiten.get(1).getName(), is("Schnupfen"));
        assertThat(krankheiten.get(2).getName(), is("Halschmerzen"));
        assertThat(krankheiten.get(3).getName(), is("Kopfschmerzen"));
        assertThat(krankheiten.get(4).getName(), is("Bauchschmerzen"));

        List<EssenLimitierung> essenLimitierungen = responseUser.getEssenLimitierungen();

        assertThat(essenLimitierungen.get(0).getName(), is("Vegetarier"));
        assertThat(essenLimitierungen.get(1).getName(), is("Laktose-Unverträglichkeit"));
        assertThat(essenLimitierungen.get(2).getName(), is("Eier-Unverträglichkeit"));
        assertThat(essenLimitierungen.get(3).getName(), is("Schokoladenunverträglichkeit"));
        assertThat(essenLimitierungen.get(4).getName(), is("Sofortiges Kotzen nach Nutellagenuss"));
        assertThat(essenLimitierungen.get(5).getName(), is("Wasserunverträglichkeit"));
        assertThat(essenLimitierungen.get(6).getName(), is("Weizenunverträglichkeit"));
        assertThat(essenLimitierungen.get(7).getName(), is("Bierunverträglichkeit"));

        assertThat(responseUser.isErlaubeMedikamentation(), is(false));

        Kontakt notfallKontakt = responseUser.getNotfallKontakt();
        assertThat(notfallKontakt.getName(), is("Andreas Müller"));
        assertThat(notfallKontakt.getAddress(), is("Werner-Heisenberg-Straße 5"));
        assertThat(notfallKontakt.getTelephone(), is("0172/34012875"));

        Arzt hausarzt = responseUser.getArzt();
        assertThat(hausarzt.getName(), is("Dr. Martin Schreiber"));
        assertThat(hausarzt.getAddress(), is("Amadeusstrasse 2"));
        assertThat(hausarzt.getTelephone(), is("0364 / 0123456"));

        assertThat(responseUser.isLiegtBehinderungVor(), is(true));
        Behinderung behinderung = responseUser.getBehinderung();

        // Merkzeichen
        assertThat(behinderung.isMerkzeichen_AussergewoehnlicheGehbehinderung_aG(), is(true));
        assertThat(behinderung.isMerkzeichen_Hilflosigkeit_H(), is(false));
        assertThat(behinderung.isMerkzeichen_Blind_Bl(), is(false));
        assertThat(behinderung.isMerkzeichen_Gehoerlos_Gl(), is(true));
        assertThat(behinderung.isMerkzeichen_BerechtigtZurMitnahmeEinerBegleitperson_B(), is(false));
        assertThat(behinderung.isMerkzeichen_ErheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G(), is(true));
        assertThat(behinderung.isMerkzeichen_Taubblind_TBL(), is(true));

        assertThat(behinderung.isRollstuhlNutzungNotwendig(), is(true));
        assertThat(behinderung.getWeitereHilfsmittel(), is("Krücken"));
        assertThat(behinderung.isWertmarkeVorhanden(), is(true));

        // Begleitperson
        assertThat(behinderung.isBegleitungNotwendig(), is(true));
        assertThat(behinderung.isBegleitpersonPflege(), is(false));
        assertThat(behinderung.isBegleitpersonMedizinischeVersorgung(), is(true));
        assertThat(behinderung.isBegleitpersonMobilitaet(), is(false));
        assertThat(behinderung.isBegleitpersonOrientierung(), is(false));
        assertThat(behinderung.isBegleitpersonSozialeBegleitung(), is(true));

        assertThat(behinderung.getEingeschraenkteSinne(), is("Sicht; Gehör; Geschmack; Geruch"));

        assertThat(behinderung.getHinweiseZumUmgangMitDemKind(), is("Bei unserem Kind ist insbesondere darauf zu achten, dass es manchmal spontan..."));
        assertThat(behinderung.isUnterstuetzungSucheBegleitpersonNotwendig(), is(true));
        assertThat(behinderung.getGewohnterBegleitpersonenDienstleister(), is("Mensch im Mittelpunkt e.V."));
        assertThat(behinderung.isBeantragungKostenuebernahmeBegleitpersonNotwendig(), is(false));

        assertThat(responseUser.isDarfAlleinNachHause(), is(true));
        assertThat(responseUser.isDarfReiten(), is(false));
        assertThat(responseUser.isDarfSchwimmen(), is(false));


    }

    private boolean containsTeilnehmer(Teilnehmer teilnehmer, List<Teilnehmer> anmeldungen) {
        for (Teilnehmer angemeldeterTeilnehmer : anmeldungen) {
            if(angemeldeterTeilnehmer.getId() == teilnehmer.getId())
                return true;
        }
        return false;
    }

    private Projekt getProjekt(List<Projekt> projekte, String projektname) {
        for (Projekt projekt : projekte) {
            System.out.println("Projekt: " + projekt.getName());
            if(projektname.equals(projekt.getName())) {
                return projekt;
            }
        }
        return null;
    }


    /****************************
     * Test project (Projekt) API
     ****************************/
    @Test
    public void addNewProjectAndetrieveItBack() {
        Projekt projekt = teilnehmerRepositoryTest.createSingleProject();
        Long projectID = addProjekt(projekt);

        Projekt responeProjekt = getProjekt(projectID);
        assertThat(projectID, is(responeProjekt.getId()));
        assertThat(responeProjekt.getName(), is(projekt.getName()));
        assertThat(responeProjekt.getSlotsFrei(), is(projekt.getSlotsFrei()));
        assertThat(responeProjekt.getKosten(), is(projekt.getKosten()));
        assertThat(responeProjekt.getAlterLimitierung(), is(projekt.getAlterLimitierung()));
        assertThat(responeProjekt.getDatum(), is(projekt.getDatum()));
        assertThat(responeProjekt.getSlotsGesamt(), is(projekt.getSlotsGesamt()));
        assertThat(responeProjekt.getWebLink(), is(projekt.getWebLink()));
        assertThat(responeProjekt.getAnmeldungen(), is(projekt.getAnmeldungen()));
    }


    @Test
    public void addProjectAndUserAndAssignProjectToUserAndRetrieveAllProjectsForThisUser() {
        Long projectID = addProjekt(teilnehmerRepositoryTest.createSingleProject());

        Long userId = addUser(teilnehmerRepositoryTest.createUser());
        List<Teilnehmer> allUsers = getAllUsers();

        assertThat(allUsers.get(allUsers.size()-1).getId(), is(userId));

        assertThat(assignUser2Projekt(projectID, userId),is(true));

        //Verify that the added user has now the project assigned
        Teilnehmer responseUser = getUser(userId);

        Projekt responseProjekt = getProjekt(projectID);
        assertThat(responseProjekt.getAnmeldungen().size(), is(1));
        assertThat(responseProjekt.getAnmeldungen().get(0).getId(), is(responseUser.getId()));
    }

    @Test
    public void addProjectUsingParametersAndTestForSuccess() {
        Projekt projekt = teilnehmerRepositoryTest.createSingleProject();
        Long projectID =
                given()
                        .param("name", projekt.getName())
                        .param("date",projekt.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .param("age",projekt.getAlterLimitierung())
                        .param("price",projekt.getKosten())
                        .param("slots",projekt.getSlotsGesamt())
                        .param("slotsReserved",projekt.getSlotsReserviert())
                        .param("weblink",projekt.getWebLink())
                .when()
                .get(BASE_URL + "/createproject")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .extract().as(Long.class);

        Projekt responseProjekt = getProjekt(projectID);

        assertThat(projectID, is(responseProjekt.getId()));
        assertThat(responseProjekt.getName(), is(projekt.getName()));
        assertThat(responseProjekt.getSlotsFrei(), is(projekt.getSlotsFrei()));
        assertThat(responseProjekt.getKosten(), is(projekt.getKosten()));
        assertThat(responseProjekt.getAlterLimitierung(), is(projekt.getAlterLimitierung()));
        assertThat(responseProjekt.getDatum(), is(projekt.getDatum()));
        assertThat(responseProjekt.getSlotsGesamt(), is(projekt.getSlotsGesamt()));
        assertThat(responseProjekt.getWebLink(), is(projekt.getWebLink()));
        assertThat(responseProjekt.getAnmeldungen(), is(projekt.getAnmeldungen()));
    }

    @Test
    public void addProjectAndSetItToInactive() {
        //Create a project
        Projekt projekt = teilnehmerRepositoryTest.createSingleProject();
        Long projectID =addProjekt(projekt);
        assertThat(projekt.isAktiv(),is(true));

        //set project inactive
        assertThat(setProjektInactive(projectID), is(true));

        //retrieve project
        Projekt responseProjekt = getProjekt(projectID);

        assertThat(projectID, is(responseProjekt.getId()));
        assertThat(projekt.getName(), is(responseProjekt.getName()));
        assertThat(responseProjekt.isAktiv(),is(false));
    }

    private Projekt getProjekt(Long projectID) {
        return given()
                .pathParam("projekt_id", projectID)
                .when()
                .get(BASE_URL + "/project/{projekt_id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .extract().as(Projekt.class);
    }

    @Test
    public void assignProjektToUserAndRetrieveAllProjectsForTheUsers() {
        //Make sure we have a user in the DB
        List<Teilnehmer> allUsers = getAllUsers();

        Long userId = 0L;

        if(allUsers.size() <= 0) {
            userId = addUser(teilnehmerRepositoryTest.createUser());
        } else
            userId = allUsers.get(0).getId();

        //Make sure we have a project in the DB
        List<Projekt> allProjects = getAllProjects();
        Long projectID = 0L;
        if(allProjects.size() <= 0) {
            Projekt projekt = teilnehmerRepositoryTest.createSingleProject();
            projectID =
                    given()
                            .param("name", projekt.getName())
                            .param("date",projekt.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                            .param("age",projekt.getAlterLimitierung())
                            .param("price",projekt.getKosten())
                            .param("slots",projekt.getSlotsGesamt())
                            .param("slotsReserved",projekt.getSlotsReserviert())
                            .param("weblink",projekt.getWebLink())
                            .when()
                            .get(BASE_URL + "/createproject")
                            .then()
                            .statusCode(HttpStatus.SC_CREATED)
                            .assertThat()
                            .extract().as(Long.class);
        } else
            projectID = allProjects.get(0).getId();

        //Assign user to project
        assertThat(assignUser2Projekt(projectID, userId),is(true));

        //Get the user again
        Teilnehmer responseUser = getUser(userId);

        //Get all projects fo the userID
        List<Projekt> projectsOfUserID = getAllProjekteWhereUserIsAssigned(responseUser);
        assertThat(projectsOfUserID.size(),is(1));
        assertThat(projectsOfUserID.get(0).getId(), is(projectID));

    }

    @Test
    public void testConsistencyOfRegisteredProjectsOfTeilnehmerAndRegisteredTeilnehmerOfProjects() {
        List<Projekt> allProjects = getAllProjects();
        int numberOfProjects = allProjects.size();
        System.out.println("Number of projects at start: " + numberOfProjects);

        // number of registered Teilnehmer in all projects should be equal
        // to number of registered projects of all Teilnehmer
        Projekt projekt1 = teilnehmerRepositoryTest.createSingleProject();
        Long projectID1 = addProjekt(projekt1);
        assertThat(projekt1.isAktiv(),is(true));

        Projekt projekt2 = teilnehmerRepositoryTest.createSingleProject();
        Long projectID2 = addProjekt(projekt2);
        assertThat(projekt2.isAktiv(),is(true));

        Teilnehmer user1 = teilnehmerRepositoryTest.createUser();
        Long userId1 = addUser(user1);

        Teilnehmer user2 = teilnehmerRepositoryTest.createUser();
        Long userId2 = addUser(user2);

        allProjects = getAllProjects();

        System.out.println("Number of projects after adding two: " + allProjects.size());
        assertThat(allProjects.size(), is(numberOfProjects+2));

        numberOfProjects = allProjects.size();
        int sumOfRegisteredTeilnehmer = 0;
        for (Projekt projekt: allProjects) {
            sumOfRegisteredTeilnehmer += projekt.getAnmeldungen().size();
        }
        System.out.println("Number of registered participants of all projects: " + sumOfRegisteredTeilnehmer);

        //Assign some projects to users
        assertThat(assignUser2Projekt(projectID1, userId1),is(true));
        assertThat(assignUser2Projekt(projectID2, userId1),is(true));
        assertThat(assignUser2Projekt(projectID1, userId2),is(true));
        assertThat(assignUser2Projekt(projectID2, userId2),is(true));

        allProjects = getAllProjects();
        System.out.println("Number of projects after assignment: " + allProjects.size());
        assertThat(allProjects.size(), is(numberOfProjects));

        int newSumOfRegisteredTeilnehmer = 0;
        for (Projekt projekt: allProjects) {
            newSumOfRegisteredTeilnehmer += projekt.getAnmeldungen().size();
        }
        //4 Assignments
        assertThat(newSumOfRegisteredTeilnehmer,is(sumOfRegisteredTeilnehmer+4));
    }

    private List<Projekt> getAllProjekteWhereUserIsAssigned(Teilnehmer responseUser) {
        return Arrays.asList(
                given()
                        .param("userID", responseUser.getId())
                        .when()
                        .get(BASE_URL+"/projectsofid")
                        .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .extract().as(Projekt[].class));
    }

    private Long addUser(Teilnehmer teilnehmer) {
        return given()
                .body(teilnehmer)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/adduser")
                .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                .body().as(Long.class);
    }

    private Long registerNewUserFromAnmeldungFrontend(AnmeldungJson anmeldungJson) {
        return given()
                .contentType(ContentType.JSON)
                .body(anmeldungJson)
                .when()
                .post(BASE_URL + "/register")
                .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                .body().as(Long.class);
    }

    private Long addProjekt(Projekt projekt) {
        return given()
                .body(projekt)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/addproject")
                .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                .body().as(Long.class);
    }

    private List<Projekt> getAllProjects() {
        return Arrays.asList(given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(BASE_URL + "/allprojects")
                        .then()
                        .statusCode(is(HttpStatus.SC_OK))
                        .extract()
                        .body().as(Projekt[].class));
    }

    private List<Teilnehmer> getAllUsers() {
        return Arrays.asList(given()
                .when()
                .get(BASE_URL + "/allusers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Teilnehmer[].class));
    }

    private Boolean assignUser2Projekt(Long projektId, Long userId) {
        Map<String,Long> newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId);
        newID_Map.put("project", projektId);
        return given()
                .body(newID_Map)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/assignProject")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Boolean.class);
    }

    private Teilnehmer getUser(Long userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .get(BASE_URL + "/user/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .extract().as(Teilnehmer.class);
    }

    private boolean setProjektInactive(Long projectID) {
        return given()
                .param("project_id", projectID)
                .when()
                .get(BASE_URL + "/deleteproject")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .extract().as(Boolean.class);
    }
}
