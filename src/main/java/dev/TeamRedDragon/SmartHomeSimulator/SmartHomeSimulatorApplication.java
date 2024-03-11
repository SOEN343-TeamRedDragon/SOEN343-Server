package dev.TeamRedDragon.SmartHomeSimulator;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SmartHomeSimulatorApplication {

	public static void main(String[] args) throws IOException, ParseException {
		Home home = new Home();
		home.jsonParser();
		SpringApplication.run(SmartHomeSimulatorApplication.class, args);
	}

}
