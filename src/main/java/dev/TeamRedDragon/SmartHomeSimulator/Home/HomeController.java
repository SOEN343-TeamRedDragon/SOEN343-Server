package dev.TeamRedDragon.SmartHomeSimulator.Home;

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

    @PostMapping("/toggleAllElements")
    public Home toggleAllElementsByType(@RequestBody Map<String, String> data)
    {
        String elementType = data.get("elementType");
        homeService.toggleAllElementsByType(elementType);
        return Home.getHome();
    }
}
