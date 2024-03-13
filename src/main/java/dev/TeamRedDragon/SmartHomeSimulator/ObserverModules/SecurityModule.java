package dev.TeamRedDragon.SmartHomeSimulator.ObserverModules;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElementObserver;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class SecurityModule implements SmartElementObserver {
    @Override
    public void update(SmartElement element) {
        if (element instanceof Door) {
            Door door = (Door) element;
            System.out.println("Security module notified: " + door);
        } else if (element instanceof Window) {
            Window window = (Window) element;
            System.out.println("Security module notified: " + window);
        }
    }
}
