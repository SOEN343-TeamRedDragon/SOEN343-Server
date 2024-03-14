package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Light extends SmartElement {

    public Light(int elementId, String elementType, boolean isOpen) {
       super(elementId, elementType, isOpen);
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