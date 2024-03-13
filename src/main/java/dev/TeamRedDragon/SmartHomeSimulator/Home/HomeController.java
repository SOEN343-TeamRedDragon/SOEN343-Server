package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.Utilities.JsonFileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HomeController")
public class HomeController {

    @GetMapping("/Home")
    public Home getHomeLayout() {
        Home home = Home.getHome();
        JsonFileService.updateHomeLayout();
        return home;
    }

    @PostMapping("/Room")
    public Room getRoomById(@RequestBody int roomId) {
        return HomeService.getRoomById(roomId);
    }

    @PostMapping("/SmartElement")
    public SmartElement getElementByRoomIdAndElementId(@RequestBody int roomId, int elementId) {
        return HomeService.getElementByRoomIdAndElementId(roomId, elementId);
    }

    @PostMapping("/ToggleSmartElement")
    public SmartElement toggleElementByRoomIdAndElementId(@RequestBody int roomId, int elementId) {
        return HomeService.toggleElementByRoomIdAndElementId(roomId, elementId);
    }
}
