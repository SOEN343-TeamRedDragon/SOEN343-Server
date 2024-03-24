package dev.TeamRedDragon.SmartHomeSimulator;

import TemperatureData.TemperatureDataService;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SmartHomeSimulatorApplication {
	private static SimulationClock simulationClock = SimulationClock.getSimulationClock();


	public static void main(String[] args) {
		SpringApplication.run(SmartHomeSimulatorApplication.class, args);


		// Start Simulation Clock
		SimulationClockService.startClock();

		Double temp = TemperatureDataService.getTemperatureFromClockAndTemperatureData();
		System.out.println(temp);

	}

}
