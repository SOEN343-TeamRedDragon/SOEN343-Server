package dev.TeamRedDragon.SmartHomeSimulator.MotionDetectors;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.stereotype.Service;

@Service
public class MotionDetectorService {

    private Home home = Home.getHome();

    SmartElement motionDetector;

    SmartElement sensor ;


    public  SmartElement addMotionDetectorById(int roomId) {
        int elementId;
        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId) {
                elementId = room.getElementCount() +1;
                sensor = new MotionDetector(elementId, "MotionDetector", true, false);
                room.addSmartElement(sensor);
                return sensor;
            }
        }
        return null;
    }
}
