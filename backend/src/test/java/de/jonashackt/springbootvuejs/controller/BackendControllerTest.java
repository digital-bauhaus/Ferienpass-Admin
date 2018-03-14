package de.jonashackt.springbootvuejs.controller;


import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import de.jonashackt.springbootvuejs.domain.Projekt;
import de.jonashackt.springbootvuejs.domain.Teilnehmer;
import de.jonashackt.springbootvuejs.repository.TeilnehmerRepositoryTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
