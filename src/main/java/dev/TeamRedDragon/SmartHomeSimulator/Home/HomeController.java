package dev.TeamRedDragon.SmartHomeSimulator.Home;


import dev.TeamRedDragon.SmartHomeSimulator.Utilities.JsonFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HomeController")
public class HomeController {

    @Autowired
    private final JsonFileService jsonFileService;


    public HomeController(JsonFileService jsonFileService) {
        this.jsonFileService = jsonFileService;
    }

    @GetMapping("/Home")
    public Home getHomeLayout() {
        return jsonFileService.readJsonFile();
    }
}
