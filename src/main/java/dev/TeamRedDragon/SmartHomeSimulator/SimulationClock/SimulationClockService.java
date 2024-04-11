package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import jakarta.annotation.PostConstruct;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class SimulationClockService {

    private static SimulationClock simulationClock = SimulationClock.getSimulationClock();
    static Thread t = new Thread(simulationClock.clockRunnable);

    private static boolean isStarted = false;

    public String getSimulationClockTime() {
        return simulationClock.getTime();
    }

    public void updateTimeSpeed(double timeSpeed) {
        t.interrupt();
        simulationClock.setTimeSpeed(timeSpeed);
    }

    public void updateSimulationTime(String date) {
        // Parse the string to an Instant
        Instant instant = Instant.parse(date);
    
        // Convert Instant to LocalDateTime in the system's default time zone
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    
        // Extract the date and time components
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int min = localDateTime.getMinute();
    
        // Update the simulation clock
        simulationClock.setDate(year, month, day, hour, min);
    }
    
    public static boolean startClock() {
        if (!isStarted) {
            t.start();
            isStarted = true;
            return true;
        }
        return false;
    }

    public boolean stopClock() {
        if (isStarted) {
            updateTimeSpeed(0);
            isStarted = false;
            return true;
        }
        return false;
    }

    public boolean getIsStarted() {
        return isStarted;
    }

    public String getTime() {
        return simulationClock.getTime();
    }

    public int getHour() {
        return Integer.parseInt(simulationClock.getTime().substring(11, 13));
    }

    public int getMonth() {
        return Integer.parseInt(simulationClock.getTime().substring(5, 7));
    }

    @PostConstruct
    public void initialize() {
        startClock();
    }

}
