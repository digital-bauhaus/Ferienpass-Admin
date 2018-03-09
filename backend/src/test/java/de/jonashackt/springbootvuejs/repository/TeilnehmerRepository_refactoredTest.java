package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.domain_refactored.*;
import de.jonashackt.springbootvuejs.repository_refactored.TeilnehmerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeilnehmerRepository_refactoredTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeilnehmerRepository users;

    Teilnehmer user = createUser();

    public static Teilnehmer createUser() {
        LocalDate registerDate = LocalDate.now();
        Arzt arzt = new Arzt("Eich", "Route 1 Alabastia, 39829",
                "555-6891");
        Kontakt kontact = new Kontakt("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        EssenLimitierung laktose = new EssenLimitierung("Laktoseintoleranz", "");
        Krankheit krank = new Krankheit("Grippe", "Muss oft Husten", "Hustenbonbons");
        List<Projekt> projects = createProjects();


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
        return user;
    }


    public static List<Projekt> createProjects() {
        Projekt project1 = new Projekt("Ball werfen", LocalDate.now(), 10, 20, 3, 1,"www.google.com", new ArrayList<>());
        return new ArrayList<Projekt>(Arrays.asList(project1));
    }

    public static Projekt createSingleProject() {
        return new Projekt("Schwimmen im See", LocalDate.now(), 15, 12, 10, 5,"www.google.com", new ArrayList<>());
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

        assertThat(usersWithLastNameEich, contains(user));
    }

    @Test
    public void testFindProjectsByFirstNameAndLastName() throws Exception {
        List<Projekt> projectsByFirstNameAndLastName = users.findProjektsByVornameAndNachname("Gary","Eich");
        String projectName = createProjects().get(0).getName();
        assertEquals(projectsByFirstNameAndLastName.get(0).getName(), projectName);
    }


    @Test
    public void testFindByFirstName() throws Exception {
        // Search for specific User in Database according to firstname
        List<Teilnehmer> usersWithFirstNameJonas = users.findByVorname("Gary");

        assertThat(usersWithFirstNameJonas, contains(user));
    }
}
