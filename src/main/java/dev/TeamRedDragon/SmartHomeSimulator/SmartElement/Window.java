package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Window extends SmartElement {

    public Window(int elementId, String elementType, boolean isOpen) {
        super(elementId, elementType, isOpen);
    }


    @Override
    public String toString() {
        return "Window {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + isOpen +
                '}';
    }
}
