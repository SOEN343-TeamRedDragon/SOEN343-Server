package dev.TeamRedDragon.SmartHomeSimulator.Mediator;

public interface Mediator {
    void notify(ModuleComponent sender, String message);
}
