package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Heater extends SmartElement {

    public Heater(int elementId, String elementType, boolean isOpen) {
        super(elementId, elementType, isOpen);
    }

    @Override
    public String toString() {
        return "Heater {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + isOpen +
                '}';
    }
}
