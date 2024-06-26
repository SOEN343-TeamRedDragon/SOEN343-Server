package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/SmartElementController")
public class SmartElementController {

    @Autowired
    private SmartElementService smartElementService;
    @PostMapping("/SmartElement")
    public SmartElement getElementByRoomIdAndElementId(@RequestBody Map<String, String> data) {
        String roomId = data.get("roomId");
        String elementId = data.get("elementId");
        return smartElementService.getElementByRoomIdAndElementId(Integer.parseInt(roomId), Integer.parseInt(elementId));
    }

    @PostMapping("/ToggleSmartElement")
    public SmartElement toggleElementByRoomIdAndElementId(@RequestBody Map<String, String> data) {
        String roomId = data.get("roomId");
        String elementId = data.get("elementId");
        return smartElementService.toggleElementByRoomIdAndElementId(Integer.parseInt(roomId), Integer.parseInt(elementId));
    }
}
