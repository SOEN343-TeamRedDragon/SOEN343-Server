package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.State.State;
import org.springframework.stereotype.Service;

@Service
public class SmartHomeSecurityModuleService {
    private SmartHomeSecurityModule smartHomeSecurityModule = SmartHomeSecurityModule.getSmartHomeSecurityModule();

    public void turnOnAwayMode() {
        smartHomeSecurityModule.turnOnAwayMode();
    }

    public void turnOffAwayMode() {
        smartHomeSecurityModule.turnOffAwayMode();
    }

    public State getState() {
        return smartHomeSecurityModule.getState();
    }

}
