package dev.TeamRedDragon.SmartHomeSimulator.State;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule.SmartHomeSecurityModule;

public abstract class State {
    protected SmartHomeSecurityModule securityModule;

    public State(SmartHomeSecurityModule securityModule) {this.securityModule = securityModule;}

    abstract public void turnOnAwayMode();
    abstract public void turnOffAwayMode();
}
