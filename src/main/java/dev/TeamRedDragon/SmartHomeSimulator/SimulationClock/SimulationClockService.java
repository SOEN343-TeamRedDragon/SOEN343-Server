package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import org.springframework.stereotype.Service;

@Service
public class SimulationClockService {

    private SimulationClock simulationClock = SimulationClock.getSimulationClock();


    public String getSimulationClockTime(){
        return simulationClock.getTime();
    }

    public void updateTimeSpeed(double timeSpeed) {
        simulationClock.setTimeSpeed(timeSpeed);
    }
}
