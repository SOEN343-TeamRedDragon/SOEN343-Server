package dev.TeamRedDragon.SmartHomeSimulator.State;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule.SmartHomeSecurityModule;

public class ActiveState extends State{

    public ActiveState(SmartHomeSecurityModule securityModule) {
        super(securityModule);
    }

    @Override
    public void turnOnAwayMode() {
        this.securityModule.changeState(new AwayState(securityModule));
    }

    @Override
    public void turnOffAwayMode() {
        System.out.println("Away Mode Already Off.");
    }
}
