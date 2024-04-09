package dev.TeamRedDragon.SmartHomeSimulator.Observer;

import java.util.ArrayList;

public interface Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers(String event);

}
