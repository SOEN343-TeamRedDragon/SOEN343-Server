package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Command.ToggleCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Room.RoomService;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HomeService {
    static Home home = Home.getHome();

    @Autowired
    private RoomService roomService;



}

