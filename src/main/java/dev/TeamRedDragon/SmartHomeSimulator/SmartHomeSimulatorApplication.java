package dev.TeamRedDragon.SmartHomeSimulator;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Home.HomeService;
import dev.TeamRedDragon.SmartHomeSimulator.Utilities.JsonFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SmartHomeSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeSimulatorApplication.class, args);
	}
}
