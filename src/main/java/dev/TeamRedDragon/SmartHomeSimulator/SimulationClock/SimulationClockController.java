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
        simulationClockService.stopClock();
        return ResponseEntity.status(HttpStatus.OK).body("Clock Stopped.");
    }

    @PostMapping("/UpdateSimulationTime")
    public ResponseEntity<Object> updateSimulationTime(@RequestBody Map<String, String> data) {
       String date = data.get("date");
         simulationClockService.updateSimulationTime(date);
        return ResponseEntity.status(HttpStatus.OK).body("Clock time updated.");
    }
}
