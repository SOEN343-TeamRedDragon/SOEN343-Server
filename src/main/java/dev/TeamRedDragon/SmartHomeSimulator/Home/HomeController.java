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

    @PostMapping("/SetAllElements")
    public Home setAllElementsStateByBooleanAndType(@RequestBody Map<String, String> data)
    {
        String isOpen = data.get("isOpen");
        String elementType = data.get("elementType");
        return homeService.setAllElementsStateByBooleanAndType(Boolean.valueOf(isOpen), elementType);
    }
}
