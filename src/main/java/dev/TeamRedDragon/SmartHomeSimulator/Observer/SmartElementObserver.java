package dev.TeamRedDragon.SmartHomeSimulator.Observer;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public interface SmartElementObserver {
    void update(SmartElement element);
}
