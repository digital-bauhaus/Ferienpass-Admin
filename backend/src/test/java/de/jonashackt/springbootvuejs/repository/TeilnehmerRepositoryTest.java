package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.FerienpassAdminApplication;
import de.jonashackt.springbootvuejs.domain.*;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = FerienpassAdminApplication.class)
public class TeilnehmerRepositoryTest {
    private static final String BASE_URL = "http://localhost:8089/api";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeilnehmerRepository users;

    @Autowired
    private ProjektRepository projects;

    private static int addedProjects = 0;

    private Teilnehmer user = createUser();

    public Teilnehmer createUser() {
        LocalDate registerDate = LocalDate.now();
        Arzt arzt = new Arzt("Eich", "Route 1 Alabastia, 39829",
                "555-6891");
        Kontakt kontact = new Kontakt("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        EssenLimitierung laktose = new EssenLimitierung("Laktoseintoleranz", "");
        Krankheit krank = new Krankheit("Grippe", "Muss oft Husten", "Hustenbonbons");


        List<EssenLimitierung> essenLimitierungen = new ArrayList<EssenLimitierung>();
        essenLimitierungen.add(laktose);
        List<Krankheit> krankheiten = new ArrayList<Krankheit>();
        krankheiten.add(krank);
        List<Allergie> allergien = new ArrayList<Allergie>();
        allergien.add(new Allergie("Heuschnupfen","Nasenspray","nur 2x am Tag"));

        Behinderung behinderung = new Behinderung();
        behinderung.setRollstuhlNutzungNotwendig(true);
        behinderung.setMerkzeichen_Taubblind_TBL(true);

        Teilnehmer user = new Teilnehmer("Gary","Eich", LocalDate.of(2005,10,20),registerDate, "Bahnhofstraße 4", "Weimar", "99423", "03544444", "0453434", true, kontact,
                true, false, false, false, arzt,  allergien, essenLimitierungen, krankheiten, true, behinderung);
        return user;
    }


    public List<Projekt> createProjects(int numberOfProjects) {
        ArrayList<Projekt> result = new ArrayList<Projekt>();
        for (int i = addedProjects; i < addedProjects+numberOfProjects; i++){
            Projekt p = new Projekt("Testprojekt " + i, LocalDate.now(), 5+i, 20, 3+i, 1,"www.google.com", new ArrayList<>());
            result.add(p);}
        return result;
    }

    public Projekt createSingleProject() {
       Projekt p = new Projekt("Schwimmen im See", LocalDate.now(), 15, 12, 10, 5,"www.google.com", new ArrayList<>());
       return p;
    }

    @Before
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(user);
    }
    @Test
    public void testFindByLastName() throws Exception {
        // Search for specific User in Database according to lastname
        List<Teilnehmer> usersWithLastNameEich = users.findByNachname("Eich");
        Teilnehmer user = usersWithLastNameEich.get(0);
        assertThat(user.getNachname(), containsString("Eich"));
    }

    @Test
    public void testFindProjectsByFirstNameAndLastName() throws Exception {
        Teilnehmer newUser = createUser();
        newUser.setVorname("Anton");
        newUser.setNachname("Tirol");


        Long userId =
                given()
                        .body(newUser)
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

        assertThat(responseUser.getNachname(),is("Tirol"));
        assertThat(responseUser.getVorname(),is("Anton"));

        Projekt p = createSingleProject();
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

        List<Projekt> allProjects = projects.findAllProjects();
        assertThat(allProjects.size(),is(1));

        Map<String,Long> newID_Map = new HashMap<String, Long>();
        newID_Map.put("user",responseUser.getId());
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
        MatcherAssert.assertThat(success,is(true));

        List<Projekt> projectsByFirstNameAndLastName =
                Arrays.asList(given()
                        .param("vorname","Anton")
                        .param("nachname","Tirol")
                        .when()
                        .get(BASE_URL+"/projectsof")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(Projekt[].class));
        assertThat(projectsByFirstNameAndLastName.size(),is(1));
    }


    @Test
    public void testFindByFirstName() throws Exception {
        // Search for specific User in Database according to firstname
        List<Teilnehmer> usersWithFirstNameJonas = users.findByVorname("Gary");

        assertThat(usersWithFirstNameJonas.get(0).getVorname(), containsString("Gary"));
    }
}
