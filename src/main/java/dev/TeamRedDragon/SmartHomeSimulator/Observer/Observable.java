package dev.TeamRedDragon.SmartHomeSimulator.Observer;

public interface Observable {
    public void attachObserver(Observer o);
    public void detachObserver(Observer o);
    public void notifyObservers(Observer o);
}
