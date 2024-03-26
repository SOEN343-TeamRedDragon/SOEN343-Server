package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @PostMapping("/UpdateTime")
    public ResponseEntity<Object> updateTime(@RequestBody Map<String, Integer> data) {
        int year = data.get("year");
        int month = data.get("month");
        int dayOfMonth = data.get("dayOfMonth");
        int hour = data.get("hour");
        int minute = data.get("minute");
        LocalDateTime dateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        simulationClockService.updateTime(dateTime);
        return ResponseEntity.status(HttpStatus.OK).body("Time successfully updated.");
    }

    @PostMapping("/Stop")
    public ResponseEntity<Object> stopSimulationClock() {
        simulationClockService.stopClock();
        return ResponseEntity.status(HttpStatus.OK).body("Clock Stopped.");
    }

}
