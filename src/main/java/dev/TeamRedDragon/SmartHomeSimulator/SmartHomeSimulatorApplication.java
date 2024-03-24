package dev.TeamRedDragon.SmartHomeSimulator;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SmartHomeSimulatorApplication {
	private static SimulationClock simulationClock = SimulationClock.getSimulationClock();
	private static Home home = Home.getHome();


	public static void main(String[] args) {
		SpringApplication.run(SmartHomeSimulatorApplication.class, args);


		// Start Simulation Clock
		SimulationClockService.startClock();
	}
}
