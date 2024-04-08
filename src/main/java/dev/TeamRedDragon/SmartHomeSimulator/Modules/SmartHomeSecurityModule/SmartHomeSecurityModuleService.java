package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observable;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.State.State;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SmartHomeSecurityModuleService implements Observer, Observable {
    private SmartHomeSecurityModule smartHomeSecurityModule = SmartHomeSecurityModule.getSmartHomeSecurityModule();

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void turnOnAwayMode() {
        smartHomeSecurityModule.turnOnAwayMode();
    }

    public void turnOffAwayMode() {
        smartHomeSecurityModule.turnOffAwayMode();
    }

    public State getState() {
        return smartHomeSecurityModule.getState();
    }

    @Override
    public void subscribe(Observer observer) {

    }

    @Override
    public void unsubscribe(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void update() {

    }
}
