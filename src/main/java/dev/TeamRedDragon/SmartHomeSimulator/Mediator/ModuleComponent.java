package dev.TeamRedDragon.SmartHomeSimulator.Mediator;

public interface ModuleComponent {
    public void sendMessage(String message);
    public void receiveMessage(String message);
}
