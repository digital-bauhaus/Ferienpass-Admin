package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import de.jonashackt.springbootvuejs.domain.Doctor;
import de.jonashackt.springbootvuejs.domain.Limitation;
import de.jonashackt.springbootvuejs.domain.Project;
import de.jonashackt.springbootvuejs.domain.User;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringBootVuejsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = "server.port = 8089"
)
public class BackendControllerTest {

	private static final String BASE_URL = "http://localhost:8089/api";

	@Test
	public void saysHello() {
		given()
		.when()
			.get(BASE_URL + "/hello")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.assertThat()
				.body(is(equalTo(BackendController.HELLO_TEXT)));
	}

	@Test
    public void addNewUserAndRetrieveItBack() {
		Date registerDate = new Date();
		Date birthDate = new Date();
		Doctor doctor = new Doctor("Eich", "Route", 1, "Alabastia", "39829",
				"555-6891");
		Project project1 = new Project("Ball werfen", registerDate, 3, 1, "www.google.com");
		List<Project> projects = new ArrayList<>();
		List<Limitation> limits = new ArrayList<>();
		User user = new User("Gary", "Eich", birthDate, registerDate, "Route", 1,
				"Neuborkia",
				"96826", "555-5262", "437647298", "Peter August 11194819",
				true, true, true, doctor,
				projects, limits);

        Long userId =
            given()
                .queryParam("firstName", "Gary")
                .queryParam("lastName", "Eich")
            .when()
                .post(BASE_URL + "/add")
            .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                    .body().as(Long.class);

	    User responseUser =
            given()
                    .pathParam("id", userId)
                .when()
                    .get(BASE_URL + "/user/{id}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .assertThat()
                        .extract().as(User.class);

	    // Did Norbert came back?
        assertThat(responseUser.getFirstName(), is("Gary"));
        assertThat(responseUser.getLastName(), is("Eich"));
    }

}
