package dev.TeamRedDragon.SmartHomeSimulator.SmartElements;

public class Heater extends SmartElements {
    private int elementId ;
    private String elementType;
    private boolean open = false;

    public Heater(int elementId, String elementType) {
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
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Heater {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + open +
                '}';
    }
}
