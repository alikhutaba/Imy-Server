package com.example.demo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AmeFinalProjectDemoApplication {

	
	/*
	 * 	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
	 */
	public static void main(String[] args) {
//		SpringApplication.run(AmeFinalProjectDemoApplication.class, args);
        new SpringApplicationBuilder(AmeFinalProjectDemoApplication.class).headless(false).run(args);

	}
	
    @EventListener(ApplicationReadyEvent.class)
    public void openBrowserAfterStartup() throws IOException, URISyntaxException {
        // open default browser after start:
        Desktop.getDesktop().browse(new URI("http://localhost:8080"));
    }

}
