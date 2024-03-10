package dev.TeamRedDragon.SmartHomeSimulator.SmartElements;

public class Door extends SmartElements {
    private int elementId ;
    private String elementType;
    private boolean isOpen = false;

    public Door(int elementId, String elementType) {
        this.elementId = elementId;
        this.elementType = elementType;
    }

    public int getElementId() {
        return elementId;
    }

    public String getElementType() {
        return elementType;
    }

    public boolean isOpen() {
        return isOpen;
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
