package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/HomeController")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/Home")
    public Home getHomeLayout() {
        return Home.getHome();
    }
}
