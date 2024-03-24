package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.SmartElementObserver;

public class SmartHomeCoreFunctionality implements SmartElementObserver {

    private static SmartHomeCoreFunctionality smartHomeCoreFunctionality;

    private SmartHomeCoreFunctionality() {}

    public static SmartHomeCoreFunctionality getSmartHomeCoreFunctionality() {
        if (smartHomeCoreFunctionality == null) {
            smartHomeCoreFunctionality = new SmartHomeCoreFunctionality();
        }
        return smartHomeCoreFunctionality;
    }

   // Observer pattern for update method for lights - need to add windows
    @Override
    public void update(SmartElement element) {
        if (element instanceof Light) {
            Light light = (Light) element;
            System.out.println("Lighting module notified: " + light);
        }
    }
}
