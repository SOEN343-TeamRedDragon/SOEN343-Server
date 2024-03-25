package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality;

import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class SmartHomeCoreFunctionality implements Observer {

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
    public void update() {
        //TODO: do thing
    }
}