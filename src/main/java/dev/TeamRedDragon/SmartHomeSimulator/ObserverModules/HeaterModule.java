package dev.TeamRedDragon.SmartHomeSimulator.ObserverModules;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElementObserver;

public class HeaterModule implements SmartElementObserver {
    @Override
    public void update(SmartElement element) {
        if (element instanceof Heater) {
            Heater heater = (Heater) element;
            System.out.println("Heating module notified: " + heater);
        }
    }
}
