package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

public class Heater extends SmartElement {
    private int temperature;

    public Heater(int elementId, String elementType, boolean isOpen) {
        super(elementId, elementType, isOpen);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Heater temperature set to: " + temperature);
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Heater {" +
                "elementId = " + elementId +
                ", elementType = '" + elementType + '\'' +
                ", isOpen = " + isOpen +
                ", temperature = " + temperature + 
                '}';
    }
}
