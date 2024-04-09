package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ConcreteMediator;
import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ModuleComponent;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observable;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;
import dev.TeamRedDragon.SmartHomeSimulator.State.ActiveState;
import dev.TeamRedDragon.SmartHomeSimulator.State.State;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartHomeSecurityModule implements ModuleComponent {
    private volatile static SmartHomeSecurityModule smartHomeSecurityModule;
    private State state;

    ConcreteMediator mediator = new ConcreteMediator();
    private SmartHomeSecurityModule(){}

    public static SmartHomeSecurityModule getSmartHomeSecurityModule() {
        if (smartHomeSecurityModule == null) {
            smartHomeSecurityModule = new SmartHomeSecurityModule();
        }
        smartHomeSecurityModule.init();
        return smartHomeSecurityModule;
    }

    @Override
    public void sendMessage(String message) {
        mediator.notify(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.print("SmartHomeSecurity Receiving a message: "+ message);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void turnOnAwayMode() {

        state.turnOnAwayMode();
    }

    public void turnOffAwayMode() {
        state.turnOffAwayMode();
    }

    public void init() {
        changeState(new ActiveState(this));
    }
}
