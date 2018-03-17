package de.jonashackt.springbootvuejs;

import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.ProjektRepository;
import de.jonashackt.springbootvuejs.repository.TeilnehmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FerienpassAdminApplication implements CommandLineRunner{

	@Autowired
	private TeilnehmerRepository teilnehmerRepository;

	@Autowired
	private ProjektRepository projektRepository;


	@Override
	public void run(String... args) throws Exception {
		createSampleUser();
		createSampleProject("Ball Werfen", 2);
	}

	private void createSampleUser() {
		Arzt arzt = new Arzt("Eich", "Route 1 Alabastia, 39829",
				"555-6891");
		Kontakt kontact = new Kontakt("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");

		List<Projekt> projects = new ArrayList<Projekt>();
		projects.add(new Projekt("Ball werfen", LocalDate.now(), 10, 20, 5, 1, "www.google.com", new ArrayList<>()));

		List<EssenLimitierung> essenLimitierungen = new ArrayList<EssenLimitierung>();
		EssenLimitierung laktose = new EssenLimitierung("Laktoseintoleranz", "");
		essenLimitierungen.add(laktose);
		List<Krankheit> krankheiten = new ArrayList<Krankheit>();
		Krankheit krank = new Krankheit("Grippe", "Muss oft Husten", "Hustenbonbons");
		krankheiten.add(krank);
		List<Allergie> allergien = new ArrayList<Allergie>();
		allergien.add(new Allergie("Heuschnupfen","Nasenspray","nur 2x am Tag"));
		Behinderung behinderung = new Behinderung();
		behinderung.setRollstuhlNutzungNotwendig(true);
		behinderung.setMerkzeichen_Taubblind_TBL(true);

		Teilnehmer user = new Teilnehmer(
		        "Gary",
                "Eich",
                LocalDate.of(2005,10,20),LocalDate.now(),
                "Bahnhofstraße 4",
                "Weimar",
                "99423",
                "03544444",
                "0453434",
                true,
                kontact,
				true,
                false,
                false,
                false,
                arzt,
                projects,
                allergien,
                essenLimitierungen,
                krankheiten,
                true,
                behinderung,
                new ArrayList<Projekt>());

		teilnehmerRepository.save(user);

		System.out.println(user.toString() + " successfully saved into DB");
	}

	private Projekt createSampleProject(String s, int i) {
		Projekt project = new Projekt(s, LocalDate.now(), 10, 20, i, 1, "www.google.com", new ArrayList<>());
		projektRepository.save(project);
		System.out.println(project.toString() + " successfully saved into DB");
		return project;
	}


	public static void main(String[] args) {
		SpringApplication.run(FerienpassAdminApplication.class, args);
	}

	// Enable CORS globally
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
