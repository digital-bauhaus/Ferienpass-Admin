package de.jonashackt.springbootvuejs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ContextConfiguration("/applicationContext.xml")
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class SpringBootVuejsApplication {
	public static void main(String[] args) {
		final ApplicationContext ctx = SpringApplication.run(SpringBootVuejsApplication.class, args);
	}
}
