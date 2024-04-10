package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observable;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.State.State;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class SmartHomeSecurityModuleService implements Observer, Observable {

    private SmartHomeSecurityModule smartHomeSecurityModule = SmartHomeSecurityModule.getSmartHomeSecurityModule();
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private Home home = Home.getHome();

    public void turnOnAwayMode() {
        smartHomeSecurityModule.turnOnAwayMode();
        closeAllDoorsAndWindows();
        notifyObservers("SecurityAway");
    }

    public void turnOffAwayMode() {
        smartHomeSecurityModule.turnOffAwayMode();
        notifyObservers("SecurityActive");
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public State getState() {
        return smartHomeSecurityModule.getState();
    }


    public void closeAllDoorsAndWindows(){
        for (Room room : home.getRoomList()) {
            for (SmartElement element : room.getSmartElementList()) {
               if (Objects.equals(element.getElementType(), "Door") || Objects.equals(element.getElementType(), "Window")) {
                    if (element.getIsOpen()) {
                        element.setIsOpen(false);
                    }
                }
            }
        }
    }

    @Override
    public void update(String event) {
        switch (event) {
            case ("Heating"):
                break;
        }
    }
}
