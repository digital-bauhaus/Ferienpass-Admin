package de.jonashackt.springbootvuejs.controller;


import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.TeilnehmerRepositoryTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SpringBootVuejsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port = 8089"
)
public class BackendControllerTest {
    private static final String BASE_URL = "http://localhost:8089/api";

    /****************************
     * Test user (Teilnehmer) API
     ****************************/
    @Test
    public void addNewUserAndRetrieveItBack() {
        Teilnehmer user = TeilnehmerRepositoryTest.createUser();

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
        Teilnehmer user = TeilnehmerRepositoryTest.createUser();

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

        //add some handicaps
        Behinderung b1 = new Behinderung("Arm", new BehinderungKodierung("A1"),true,false, false, true);
        Behinderung b2 = new Behinderung("Bein", new BehinderungKodierung("A2"),true,false, false, true);
        user.setBehinderungen(new ArrayList<>());
        user.getBehinderungen().add(b1);
        user.getBehinderungen().add(b2);
        assertThat(user.getBehinderungen().size(), is(2));

        //add some projects
        List<Projekt> projekte = TeilnehmerRepositoryTest.createProjects(3);
        user.setAngemeldeteProjekte(projekte);
        assertThat(user.getAngemeldeteProjekte().size(), is(3));

        //add some canceld projects
        List<Projekt> stornierteProjekte =TeilnehmerRepositoryTest.createProjects(2);
        user.setStornierungen(stornierteProjekte);
        assertThat(user.getStornierungen().size(), is(2));

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
        assertThat(responseUser.getBehinderungen().size(), is(2));
        assertThat(responseUser.getAngemeldeteProjekte().size(), is(3));
        assertThat(responseUser.getStornierungen().size(), is(2));

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

        //remove first handicap
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.behinderungen.ordinal()).longValue());
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
        assertThat(responseUser.getBehinderungen().size(),is(1));
        assertThat(responseUser.getBehinderungen().get(0).getName(),is(b2.getName()));

        //remove third and first project from registered projects
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.angemeldeteProjekte.ordinal()).longValue());
        newID_Map.put("item", new Long(2L));
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
        assertThat(responseUser.getAngemeldeteProjekte().size(),is(2));
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.angemeldeteProjekte.ordinal()).longValue());
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
        assertThat(responseUser.getAngemeldeteProjekte().size(),is(1));
        assertThat(responseUser.getAngemeldeteProjekte().get(0).getName(),is(projekte.get(1).getName()));

        //Remove fist canceled project
        newID_Map = new HashMap<String, Long>();
        newID_Map.put("user_id",userId);
        newID_Map.put("type", new Integer(ListType.stornierteProjekte.ordinal()).longValue());
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
        assertThat(responseUser.getStornierungen().size(),is(1));
        assertThat(responseUser.getStornierungen().get(0).getName(),is(stornierteProjekte.get(1).getName()));
    }

    @Test
    public void addTwoUsersAndCheckWhetherAllUsersIsComplete() {
        List<Teilnehmer> allUsers_initial =
                Arrays.asList(given()
                        .when()
                        .get(BASE_URL + "/allusers")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Teilnehmer[].class));
        int initialSize = allUsers_initial.size();

        Teilnehmer user = TeilnehmerRepositoryTest.createUser();

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

    /****************************
     * Test project (Projekt) API
     ****************************/
    @Test
    public void addNewProjectAndetrieveItBack() {
        Projekt p = TeilnehmerRepositoryTest.createSingleProject();
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
        Projekt p = TeilnehmerRepositoryTest.createSingleProject();
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

        Teilnehmer user = TeilnehmerRepositoryTest.createUser();
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
        int nbUsers = allUsers.size();

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


        assertThat(responseUser.getAngemeldeteProjekte().get(responseUser.getAngemeldeteProjekte().size()-1).getId(), is(projectID));

        //Check whether we do not have added another user to make sure that we just updated a user
        List<Teilnehmer> allUsers2 =
                Arrays.asList(given()
                        .when()
                        .get(BASE_URL + "/allusers")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Teilnehmer[].class));
        assertThat(nbUsers,is(allUsers2.size()));
    }

    @Test
    public void addProjectUsingParametersAndTestForSuccess() {
        Projekt p = TeilnehmerRepositoryTest.createSingleProject();
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
        Projekt p = TeilnehmerRepositoryTest.createSingleProject();
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
}
