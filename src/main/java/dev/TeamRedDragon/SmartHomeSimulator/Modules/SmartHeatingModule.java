package dev.TeamRedDragon.SmartHomeSimulator.Modules;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.SmartElementObserver;

public class SmartHeatingModule implements SmartElementObserver {

    // Observer pattern for update method for heating
    @Override
    public void update(SmartElement element) {
        if (element instanceof Heater) {
            Heater heater = (Heater) element;
            System.out.println("Heating module notified: " + heater);
        }
    }
}
