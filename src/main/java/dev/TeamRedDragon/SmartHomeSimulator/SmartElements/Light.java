package dev.TeamRedDragon.SmartHomeSimulator.SmartElements;

public class Light extends SmartElements {
    private int elementId ;
    private String elementType;
    private boolean isOpen = false;

    public Light(int elementId, String elementType, boolean isOpen) {
        this.elementId = elementId;
        this.elementType = elementType;
    }

    public int getElementId() {
        return elementId;
    }

    public String getElementType() { return elementType; }
    public boolean isOpen() {
        return isOpen;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "Light {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + isOpen +
                '}';
    }
}
