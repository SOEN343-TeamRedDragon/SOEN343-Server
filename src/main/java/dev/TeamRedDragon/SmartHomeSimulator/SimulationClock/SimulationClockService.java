package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import org.springframework.stereotype.Service;

@Service
public class SimulationClockService {

    private static SimulationClock simulationClock = SimulationClock.getSimulationClock();
    static Thread t = new Thread(simulationClock.clockRunnable);

    private static boolean isStarted = false;


    public String getSimulationClockTime(){
        return simulationClock.getTime();
    }

    public void updateTimeSpeed(double timeSpeed) {
        t.interrupt();
        simulationClock.setTimeSpeed(timeSpeed);
    }

    public static boolean startClock(){
        if (!isStarted){
            t.start();
            isStarted = true;
            return true;
        }
        return false;
    }

    public boolean stopClock() {
        if(isStarted){
            updateTimeSpeed(0);
            isStarted = false;
            return true;
        }
        return false;
    }

    public boolean getIsStarted() {return isStarted;}

    public String getTime() { return simulationClock.getTime();}


}
