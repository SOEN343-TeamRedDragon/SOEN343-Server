package dev.TeamRedDragon.SmartHomeSimulator.ObserverModules;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElementObserver;

public class LightModule implements SmartElementObserver {
    @Override
    public void update(SmartElement element) {
        if (element instanceof Light) {
            Light light = (Light) element;
            System.out.println("Lighting module notified: " + light);
        }
    }
}
