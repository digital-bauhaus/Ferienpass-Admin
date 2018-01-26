package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository users;


    User user = createUser();

    public static User createUser() {
        List<Limitation> limits = new ArrayList<>();

        Doctor doctor = new Doctor("Eich", "Route1 Alabastia 39829",
                "555-6891");
        Contact contact = new Contact("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");

        return new User("Gary", "Eich", "10.01.1999", createDateString(), "Route 1",
                "Neuborkia",
                "96826", "555-5262", "437647298", false,  contact,
                true, true, true, true, doctor,
                createProjects(), limits, null);    }

    private static List<Project> createProjects() {
        Project project1 = new Project("Ball werfen", createDateString(), 10, 20,  3, 1, "www.google.com", new ArrayList<>());
        return new ArrayList<>(Arrays.asList(project1));
    }

    private static String createDateString() {
        return new SimpleDateFormat("dd.mm.yyyy").format(new Date());
    }

    @Before
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(user);
    }
    @Test
    public void testFindByLastName() throws Exception {
        // Search for specific User in Database according to lastname
        List<User> usersWithLastNameEich = users.findByLastName("Eich");

        assertThat(usersWithLastNameEich, contains(user));
    }

    @Test
    public void testFindProjectsByFirstNameAndLastName() throws Exception {
        List<Project> projectsByFirstNameAndLastName = users.findProjectsByFirstNameAndLastName("Gary","Eich");
        String projectName = createProjects().get(0).getName();
        assertEquals(projectsByFirstNameAndLastName.get(0).getName(), projectName);
    }


    @Test
    public void testFindByFirstName() throws Exception {
        // Search for specific User in Database according to firstname
        List<User> usersWithFirstNameJonas = users.findByFirstName("Gary");

        assertThat(usersWithFirstNameJonas, contains(user));
    }
}