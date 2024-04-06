package dev.TeamRedDragon.SmartHomeSimulator.MotionDetectors;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class MotionDetector extends SmartElement {

    private boolean isTriggered;

    public MotionDetector(int elementId, String elementType, boolean isOpen, boolean triggered) {
        super(elementId, elementType, isOpen);
        this.isTriggered = triggered;
    }

    @Override
    public String toString() {
        return "MotionDetector{" +
                ", elementId=" + elementId +
                ", elementType='" + elementType + '\'' +
                ", isOpen=" + isOpen +
                ", isTriggered=" + isTriggered +
                '}';
    }

    public boolean getIsTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean triggered) {
        isTriggered = triggered;
    }





}
