package dev.TeamRedDragon.SmartHomeSimulator;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Utilities.JsonFileService;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SmartHomeSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeSimulatorApplication.class, args);

		Home home = Home.getHome();
		try {
			JsonFileService.updateHomeObjectFromJSON();
		} catch (Exception e) {
			System.out.println("Error updating home object from home layout file");
		}
	}
}
