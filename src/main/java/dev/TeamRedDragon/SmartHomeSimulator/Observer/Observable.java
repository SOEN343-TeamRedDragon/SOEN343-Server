package dev.TeamRedDragon.SmartHomeSimulator.Observer;

import java.util.ArrayList;

public interface Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer);

    public void unsubscribe(Observer observer);

    public void notifyObservers(String event);

}
