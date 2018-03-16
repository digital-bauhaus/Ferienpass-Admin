package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.FerienpassAdminApplication;
import de.jonashackt.springbootvuejs.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = FerienpassAdminApplication.class)
public class TeilnehmerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeilnehmerRepository users;

    private static int addedProjects = 0;

    private Teilnehmer user = createUser();

    public static Teilnehmer createUser() {
        LocalDate registerDate = LocalDate.now();
        Arzt arzt = new Arzt("Eich", "Route 1 Alabastia, 39829",
                "555-6891");
        Kontakt kontact = new Kontakt("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        EssenLimitierung laktose = new EssenLimitierung("Laktoseintoleranz", "");
        Krankheit krank = new Krankheit("Grippe", "Muss oft Husten", "Hustenbonbons");
        List<Projekt> projects = createProjects(1);


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


    public static List<Projekt> createProjects(int numberOfProjects) {
        ArrayList<Projekt> result = new ArrayList<Projekt>();
        for (int i = addedProjects; i < addedProjects+numberOfProjects; i++)
            result.add(new Projekt("Testprojekt " + i, LocalDate.now(), 5+i, 20, 3+i, 1,"www.google.com", new ArrayList<>()));
        return result;
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
        Teilnehmer user = usersWithLastNameEich.get(0);
        assertThat(user.getNachname(), containsString("Eich"));
    }

    @Test
    public void testFindProjectsByFirstNameAndLastName() throws Exception {
        List<Teilnehmer> usersWithLastNameEich = users.findByNachname("Eich");
        Teilnehmer user = usersWithLastNameEich.get(0);
        List<Projekt> projectsByFirstNameAndLastName = users.findProjektsByVornameAndNachname("Gary","Eich");
        assertEquals(projectsByFirstNameAndLastName.get(0).getName(), user.getAngemeldeteProjekte().get(0).getName());
    }


    @Test
    public void testFindByFirstName() throws Exception {
        // Search for specific User in Database according to firstname
        List<Teilnehmer> usersWithFirstNameJonas = users.findByVorname("Gary");

        assertThat(usersWithFirstNameJonas.get(0).getVorname(), containsString("Gary"));
    }
}
