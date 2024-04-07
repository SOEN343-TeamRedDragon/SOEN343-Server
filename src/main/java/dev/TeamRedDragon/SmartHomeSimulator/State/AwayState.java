package dev.TeamRedDragon.SmartHomeSimulator.State;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule.SmartHomeSecurityModule;

public class AwayState extends State{


    public AwayState(SmartHomeSecurityModule securityModule) {
        super(securityModule);
    }

    @Override
    public void turnOffAwayMode() {

        this.securityModule.changeState(new ActiveState(securityModule));

    }

    @Override
    public void turnOnAwayMode(){
        System.out.println("Away Mode Already On.");
    }
}
