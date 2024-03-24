package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/SimClock")
public class SimulationClockController {

    @Autowired
    private SimulationClockService simulationClockService;

    @GetMapping("/")
    public String getSimulationClockTime() {
        return simulationClockService.getSimulationClockTime();
    }

    @PostMapping("/UpdateTimeSpeed")
    public ResponseEntity<Object> updateTimeSpeed(@RequestBody Map<String, String> data) {
        double timeSpeed = Double.parseDouble(data.get("timeSpeed"));
        simulationClockService.updateTimeSpeed(timeSpeed);
        return ResponseEntity.status(HttpStatus.OK).body("Time speed successfully updated.");
    }

    @PostMapping("/Stop")
    public ResponseEntity<Object> stopSimulationClock() {
        simulationClockService.updateTimeSpeed(0);
        return ResponseEntity.status(HttpStatus.OK).body("Clock Stopped.");
    }

    @PostMapping("/Start")
    public ResponseEntity<Object> startSimulationClock() {
        simulationClockService.updateTimeSpeed(1);
        return ResponseEntity.status(HttpStatus.OK).body("Clock Started.");
    }
}
