package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class SmartHomeSecurityModule implements Observer {
    private static SmartHomeSecurityModule smartHomeSecurityModule;

    private SmartHomeSecurityModule(){}
    public static SmartHomeSecurityModule getSmartHomeSecurityModule() {
        if (smartHomeSecurityModule == null) {
            smartHomeSecurityModule = new SmartHomeSecurityModule();
        }
        return smartHomeSecurityModule;
    }

    @Override
    public void update() {
        //TODO: do stuff
    }
}
