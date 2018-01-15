package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import de.jonashackt.springbootvuejs.domain.*;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;
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
    public void addNewUserAndRetrieveItBack() {/*
        Date birthDate = new Date();
        Doctor doctor = new Doctor("Eich", "Route", 1, "Alabastia", "39829",
                "555-6891");
        Contact contact = new Contact("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        List<Project> projects = new ArrayList<>();
        List<Limitation> limits = new ArrayList<>();
        Long userId =
            given()
                    .queryParam("firstName", "Gary")
                    .queryParam("lastName", "Eich")
                    .queryParam("birthDate", birthDate)
                    .queryParam("street", "Route 1")
                    .queryParam("city", "Neuborkia")
                    .queryParam("postcode", "96826")
                    .queryParam("telephone", "555-5262")
                    .queryParam("healthcareNr", "437647298")
                    .queryParam("allowTreatment", false)
                    .queryParam("contact", contact)
                    .queryParam("allowHomeAlone", true)
                    .queryParam("allowRiding", false)
                    .queryParam("allowSwimming", true)
                    .queryParam("doctor", doctor)
                    .queryParam("projects", projects)
                    .queryParam("limits", limits)

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

        assertThat(responseUser.getFirstName(), is("Gary"));
        assertThat(responseUser.getLastName(), is("Eich"));*/
    }

}
