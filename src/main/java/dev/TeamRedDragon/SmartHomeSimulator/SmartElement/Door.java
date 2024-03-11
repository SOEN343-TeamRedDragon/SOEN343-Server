package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Door extends SmartElement {

    public Door(int elementId, String elementType, boolean isOpen) {
        super(elementId, elementType, isOpen);
    }

    @Override
    public String toString() {
        return "Door {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + isOpen +
                '}';
    }
}
