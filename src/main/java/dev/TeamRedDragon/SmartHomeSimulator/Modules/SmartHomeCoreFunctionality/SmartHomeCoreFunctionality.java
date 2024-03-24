package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality;

import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.SmartElementObserver;

public class SmartHomeCoreFunctionality implements SmartElementObserver {

    private static SmartHomeCoreFunctionality smartHomeCoreFunctionality;

    private SmartHomeCoreFunctionality() {}

    public SmartHomeCoreFunctionality getSmartHomeCoreFunctionality() {
        if (smartHomeCoreFunctionality == null) {
            smartHomeCoreFunctionality = new SmartHomeCoreFunctionality();
            SimulationClock simulationClock = SimulationClock.getSimulationClock();
            simulationClock.addObserver(smartHomeCoreFunctionality);
        }
        return smartHomeCoreFunctionality;
    }

   // Observer pattern for update method for lights - need to add windows
    @Override
    public void update() {

    }
}
