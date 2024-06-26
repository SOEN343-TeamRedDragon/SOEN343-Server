package dev.TeamRedDragon.SmartHomeSimulator;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModuleService;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SmartHomeSimulatorApplication {

	public static void main(String[] args) {


		SpringApplication.run(SmartHomeSimulatorApplication.class, args);

		SimulationClockService.startClock();
		SmartHeatingModuleService smartHeatingModuleService = new SmartHeatingModuleService();
		SimulationClock.getSimulationClock().subscribe(smartHeatingModuleService);

	}
}
