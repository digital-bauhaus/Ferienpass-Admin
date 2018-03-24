package de.jonashackt.springbootvuejs.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.FerienpassAdminApplication;
import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.TeilnehmerRepository;
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

        Long userId =
                given()
                        .body(user)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);

        Teilnehmer responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);

        assertThat(responseUser.getVorname(), is(user.getVorname()));
        assertThat(responseUser.getNachname(), is(user.getNachname()));
    }

    @Test
    public void addNewUserAddSeveralListItemsAndRemoveThemAgain() {
        Teilnehmer user = teilnehmerRepositoryTest.createUser();

        //add some allergies
        Allergie a1 = new Allergie("Arbeiten","Viele Aufgaben","Viel reden");
        Allergie a2 = new Allergie("Freizeit","Urlaub","Spaß haben");
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


        Long userId =
                given()
                        .body(user)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);

        Teilnehmer responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);

        assertThat(responseUser.getAllergien().size(), is(2));
        assertThat(responseUser.getEssenLimitierungen().size(), is(2));
        assertThat(responseUser.getKrankheiten().size(), is(2));

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
        responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);
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
        responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);
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
        responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);
        assertThat(responseUser.getKrankheiten().size(),is(1));
        assertThat(responseUser.getKrankheiten().get(0).getName(),is(k1.getName()));

    }

    @Test
    public void addTwoUsersAndCheckWhetherAllUsersAreComplete() {
        List<Teilnehmer> allUsers_initial =
                Arrays.asList(given()
                        .when()
                        .get(BASE_URL + "/allusers")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Teilnehmer[].class));
        int initialSize = allUsers_initial.size();

        Teilnehmer user = teilnehmerRepositoryTest.createUser();

        Long userId =
                given()
                        .body(user)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);
        Long userId2 =
                given()
                        .body(user)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);
        List<Teilnehmer> allUsers =
                Arrays.asList(given()
                        .when()
                        .get(BASE_URL + "/allusers")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Teilnehmer[].class));

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

        Long userId =
                given()
                        .contentType(ContentType.JSON)
                        .body(anmeldungJson)
                .when()
                        .post(BASE_URL + "/register")
                .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);

        Teilnehmer responseUser =
                given()
                        .pathParam("id", userId)
                .when()
                        .get(BASE_URL + "/user/{id}")
                .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);

        assertThat(responseUser.getVorname(), is("Paul"));
        assertThat(responseUser.getNachname(), is("Siegmund"));
        assertThat(responseUser.getGeburtsdatum(), is(LocalDate.of(2019,1,10)));
        assertThat(responseUser.getStrasse(), is("Rainer-Maria-Rilke-Strasse 33"));
        assertThat(responseUser.getPostleitzahl(), is("99423"));
        assertThat(responseUser.getStadt(), is("Weimar"));
        assertThat(responseUser.getTelefon(), is("03643 / 123456"));

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

    }


    /****************************
     * Test project (Projekt) API
     ****************************/
    @Test
    public void addNewProjectAndetrieveItBack() {
        Projekt p = teilnehmerRepositoryTest.createSingleProject();
        Long projectID =
                given()
                .body(p)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/addproject")
                .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                .body().as(Long.class);

        Projekt p_retrieved =
                given()
                .pathParam("projekt_id", projectID)
                .when()
                .get(BASE_URL + "/project/{projekt_id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .extract().as(Projekt.class);
        assertThat(projectID, is(p_retrieved.getId()));
        assertThat(p_retrieved.getName(), is(p.getName()));
        assertThat(p_retrieved.getSlotsFrei(), is(p.getSlotsFrei()));
        assertThat(p_retrieved.getKosten(), is(p.getKosten()));
        assertThat(p_retrieved.getAlterLimitierung(), is(p.getAlterLimitierung()));
        assertThat(p_retrieved.getDatum(), is(p.getDatum()));
        assertThat(p_retrieved.getSlotsGesamt(), is(p.getSlotsGesamt()));
        assertThat(p_retrieved.getWebLink(), is(p.getWebLink()));
        assertThat(p_retrieved.getAnmeldungen(), is(p.getAnmeldungen()));
    }


    @Test
    public void addProjectAndUserAndAssignProjectToUserAndRetrieveAllProjectsForThisUser() {
        Projekt p = teilnehmerRepositoryTest.createSingleProject();
        Long projectID =
                given()
                        .body(p)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/addproject")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);

        Teilnehmer user = teilnehmerRepositoryTest.createUser();
        Long userId =
                given()
                        .body(user)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);

        List<Teilnehmer> allUsers =
                Arrays.asList(given()
                .when()
                .get(BASE_URL + "/allusers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Teilnehmer[].class));

        assertThat(allUsers.get(allUsers.size()-1).getId(), is(userId));

        Map<String,Long> newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId);
        newID_Map.put("project", projectID);
        Boolean success =
                given()
                    .body(newID_Map)
                    .contentType(ContentType.JSON)
                    .when()
                    .post(BASE_URL+"/assignProject")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(Boolean.class);
        assertThat(success,is(true));

        //Verify that the added user has now the project assigned
        Teilnehmer responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);

        Projekt p_retrieved =
                given()
                        .pathParam("projekt_id", projectID)
                        .when()
                        .get(BASE_URL + "/project/{projekt_id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Projekt.class);
        assertThat(p_retrieved.getAnmeldungen().size(), is(1));
        assertThat(p_retrieved.getAnmeldungen().get(0).getId(), is(responseUser.getId()));
    }

    @Test
    public void addProjectUsingParametersAndTestForSuccess() {
        Projekt p = teilnehmerRepositoryTest.createSingleProject();
        Long projectID =
                given()
                        .param("name", p.getName())
                        .param("date",p.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .param("age",p.getAlterLimitierung())
                        .param("price",p.getKosten())
                        .param("slots",p.getSlotsGesamt())
                        .param("slotsReserved",p.getSlotsReserviert())
                        .param("weblink",p.getWebLink())
                .when()
                .get(BASE_URL + "/createproject")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .extract().as(Long.class);

        Projekt p_retrieved =
                given()
                        .pathParam("projekt_id", projectID)
                        .when()
                        .get(BASE_URL + "/project/{projekt_id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Projekt.class);

        assertThat(projectID, is(p_retrieved.getId()));
        assertThat(p_retrieved.getName(), is(p.getName()));
        assertThat(p_retrieved.getSlotsFrei(), is(p.getSlotsFrei()));
        assertThat(p_retrieved.getKosten(), is(p.getKosten()));
        assertThat(p_retrieved.getAlterLimitierung(), is(p.getAlterLimitierung()));
        assertThat(p_retrieved.getDatum(), is(p.getDatum()));
        assertThat(p_retrieved.getSlotsGesamt(), is(p.getSlotsGesamt()));
        assertThat(p_retrieved.getWebLink(), is(p.getWebLink()));
        assertThat(p_retrieved.getAnmeldungen(), is(p.getAnmeldungen()));
    }

    @Test
    public void addProjectAndSetItToInactive() {
        //Createi a project
        Projekt p = teilnehmerRepositoryTest.createSingleProject();
        Long projectID =
                given()
                        .body(p)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/addproject")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);
        assertThat(p.isAktiv(),is(true));

        //set project inactive
        Boolean inactive =
                given()
                .param("project_id", projectID)
                .when()
                .get(BASE_URL + "/deleteproject")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .extract().as(Boolean.class);

        //retrieve project
        Projekt p_retrieved =
                given()
                        .pathParam("projekt_id", projectID)
                        .when()
                        .get(BASE_URL + "/project/{projekt_id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Projekt.class);

        assertThat(projectID, is(p_retrieved.getId()));
        assertThat(p.getName(), is(p_retrieved.getName()));
        assertThat(p_retrieved.isAktiv(),is(false));
    }

    @Test
    public void assignProjektToUserAndRetrieveAllProjectsForTheUsers() {
        //Make sure we have a user in the DB
        List<Teilnehmer> allUsers =
                Arrays.asList(given()
                        .when()
                        .get(BASE_URL + "/allusers")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Teilnehmer[].class));

        Long userId = 0L;

        if(allUsers.size() <= 0) {
            Teilnehmer user = teilnehmerRepositoryTest.createUser();
            userId =
                    given()
                            .body(user)
                            .contentType(ContentType.JSON)
                            .when()
                            .post(BASE_URL + "/adduser")
                            .then()
                            .statusCode(is(HttpStatus.SC_CREATED))
                            .extract()
                            .body().as(Long.class);
        } else
            userId = allUsers.get(0).getId();

        //Make sure we have a project in the DB
        List<Projekt> allProjects =
                Arrays.asList(given()
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/allprojects")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Projekt[].class));
        Long projectID = 0L;
        if(allProjects.size() <= 0) {
            Projekt p = teilnehmerRepositoryTest.createSingleProject();
            projectID =
                    given()
                            .param("name", p.getName())
                            .param("date",p.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                            .param("age",p.getAlterLimitierung())
                            .param("price",p.getKosten())
                            .param("slots",p.getSlotsGesamt())
                            .param("slotsReserved",p.getSlotsReserviert())
                            .param("weblink",p.getWebLink())
                            .when()
                            .get(BASE_URL + "/createproject")
                            .then()
                            .statusCode(HttpStatus.SC_CREATED)
                            .assertThat()
                            .extract().as(Long.class);
        } else
            projectID = allProjects.get(0).getId();

        //Assign user to project
        Map<String,Long> newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId);
        newID_Map.put("project", projectID);
        Boolean success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/assignProject")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));

        //Get the user again
        Teilnehmer responseUser =
                given()
                        .pathParam("id", userId)
                        .when()
                        .get(BASE_URL + "/user/{id}")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Teilnehmer.class);

        //Get all projects fo the userID
        List<Projekt> projectsOfUserID =
                Arrays.asList(
                        given()
                                .param("userID", responseUser.getId())
                                .when()
                                .get(BASE_URL+"/projectsofid")
                                .then()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .extract().as(Projekt[].class));
        assertThat(projectsOfUserID.size(),is(1));
        assertThat(projectsOfUserID.get(0).getId(), is(projectID));

        //"/projectRegistrations/{projekt_id}

    }

    @Test
    public void testConsistencyOfRegisteredProjectsOfTeilnehmerAndRegisteredTeilnehmerOfProjects() {
        List<Projekt> allProjects =
                Arrays.asList(given()
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/allprojects")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Projekt[].class));
        int nbProjects = allProjects.size();
        System.out.println("Number of projects at start: " + nbProjects);

        //number of registered Teilnehmer in all projects should be equal to number of registered projects of all Teilnehmer
        Projekt p1 = teilnehmerRepositoryTest.createSingleProject();
        Long projectID1 =
                given()
                        .body(p1)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/addproject")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);
        assertThat(p1.isAktiv(),is(true));
        Projekt p2 = teilnehmerRepositoryTest.createSingleProject();
        Long projectID2 =
                given()
                        .body(p2)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/addproject")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);
        assertThat(p2.isAktiv(),is(true));

        Teilnehmer user1 = teilnehmerRepositoryTest.createUser();
        Long userId1 =
                given()
                        .body(user1)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);
        Teilnehmer user2 = teilnehmerRepositoryTest.createUser();
        Long userId2 =
                given()
                        .body(user2)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/adduser")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Long.class);

        allProjects =
                Arrays.asList(given()
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/allprojects")
                .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                .body().as(Projekt[].class));

        System.out.println("Number of projects after adding two: " + allProjects.size());
        assertThat(allProjects.size(), is(nbProjects+2));
        nbProjects = allProjects.size();
        int sumOfRegisteredTeilnehmer = 0;
        for (Projekt p: allProjects) {
            sumOfRegisteredTeilnehmer += p.getAnmeldungen().size();
        }
        System.out.println("Number of registered participants of all projects: " + sumOfRegisteredTeilnehmer);


        //Assign some projects to users
        Map<String,Long> newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId1);
        newID_Map.put("project", projectID1);
        Boolean success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/assignProject")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId1);
        newID_Map.put("project", projectID2);
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/assignProject")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));

        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId2);
        newID_Map.put("project",projectID1);
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/assignProject")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",userId2);
        newID_Map.put("project", projectID2);
        success =
                given()
                        .body(newID_Map)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL+"/assignProject")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Boolean.class);
        assertThat(success,is(true));


        allProjects =
                Arrays.asList(given()
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/allprojects")
                        .then()
                        .statusCode(is(HttpStatus.SC_CREATED))
                        .extract()
                        .body().as(Projekt[].class));
        System.out.println("Number of projects after assignment: " + allProjects.size());
        assertThat(allProjects.size(), is(nbProjects));


        int newSumOfRegisteredTeilnehmer = 0;
        for (Projekt p: allProjects) {
            newSumOfRegisteredTeilnehmer += p.getAnmeldungen().size();
        }
        //4 Assignments
        assertThat(newSumOfRegisteredTeilnehmer,is(sumOfRegisteredTeilnehmer+4));
    }
}
