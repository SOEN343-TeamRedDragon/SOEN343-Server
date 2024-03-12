package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "classType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Door.class, name = "Door"),
        @JsonSubTypes.Type(value = Heater.class, name = "Heater"),
        @JsonSubTypes.Type(value = Light.class, name = "Light"),
        @JsonSubTypes.Type(value = Window.class, name = "Window")
})

public abstract class SmartElement {
    protected int elementId;
    protected String elementType;
    protected boolean isOpen;
    private List<SmartElementObserver> observers = new ArrayList<>();

    public SmartElement(int elementId, String elementType, boolean isOpen) {
        this.elementId = elementId;
        this.elementType = elementType;
        this.isOpen = isOpen;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }
    public void toggle(){
        isOpen = !isOpen;
    }

    public void attach(SmartElementObserver observer) {
        observers.add(observer);
    }

    public void detach(SmartElementObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (SmartElementObserver observer : observers) {
            observer.update(this);
        }
    }
}
