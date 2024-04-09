package dev.TeamRedDragon.SmartHomeSimulator.MotionDetectors;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/MotionDetectorController")
public class MotionDetectorController {

    @Autowired
    MotionDetectorService motionDetectorService;


    @PostMapping("/AddByRoomId")
    public SmartElement setMotionDetectorServiceByRoomById(@RequestBody Map<String, String> data) {
        String roomId = data.get("roomId");
        return motionDetectorService.addMotionDetectorById(Integer.parseInt(roomId));
    }

}
