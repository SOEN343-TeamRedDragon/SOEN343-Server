package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class AirConditioner extends SmartElement {

    public AirConditioner(int elementId, String elementType, boolean isOpen) {
        super(elementId, elementType, isOpen);
    }

    @Override
    public String toString() {
        return "AirConditioner{" +
                "elementId=" + elementId +
                ", elementType='" + elementType + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }
}
