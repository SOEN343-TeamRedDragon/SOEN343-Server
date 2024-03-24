package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.SmartElementObserver;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class SmartHomeSecurityModule implements SmartElementObserver {
    private static SmartHomeSecurityModule smartHomeSecurityModule;

    private SmartHomeSecurityModule(){}
    public SmartHomeSecurityModule getSmartHomeSecurityModule() {
        if (smartHomeSecurityModule == null) {
            smartHomeSecurityModule = new SmartHomeSecurityModule();
            SimulationClock simulationClock = SimulationClock.getSimulationClock();
            simulationClock.addObserver(smartHomeSecurityModule);
        }
        return smartHomeSecurityModule;
    }

    @Override
    public void update() {

    }
}
