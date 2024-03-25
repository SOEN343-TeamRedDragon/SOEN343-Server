package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Window extends SmartElement {

    public boolean isBlocked = false;

    public Window(int elementId, String elementType, boolean isOpen) {
        super(elementId, elementType, isOpen);
    }

    public boolean blockWindow() {
        System.out.println("Window with the ID: " + this.elementId + " is now blocked.");
        return isBlocked = true;
    }

    public boolean unblockWindow() {
        System.out.println("Window with the ID: " + this.elementId + " is now unblocked.");
        return isBlocked = false;
    }

    public boolean isWindowBlocked() {
        return isBlocked;
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
