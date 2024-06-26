package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Window extends SmartElement {

    private boolean isBlocked;

    public Window(int elementId, String elementType, boolean isOpen, boolean isBlocked) {
        super(elementId, elementType, isOpen);
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "Window {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + isOpen +
                '}';
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }
}
