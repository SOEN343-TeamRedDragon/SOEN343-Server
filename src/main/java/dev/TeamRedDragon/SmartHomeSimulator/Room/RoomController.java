package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/RoomController")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/Room")
    public Room getRoomById(@RequestBody String roomId) {
        return roomService.getRoomById(Integer.parseInt(roomId));
    }

    @PostMapping("/ToggleRoom")
    public Home toggleAllElementsInRoomByRoomIdAndElementType(@RequestBody Map<String, String> data) {
        String roomId = data.get("roomId");
        String elementType = data.get("elementType");
        roomService.toggleAllElementsInRoomByRoomIdAndElementType(Integer.parseInt(roomId), elementType);
        return Home.getHome();
    }

    @PostMapping("/ChangeUserLocation")
    public Home changeUserLocationByUserNameAndRoomId(@RequestBody Map<String, String> data) {
        String userName = data.get("userName");
        String roomId = data.get("roomId");
        roomService.changeUserLocationByNewRoomIdAndUserName(Integer.parseInt(roomId), userName);
        return Home.getHome();
    }

    @PostMapping("/RemoveUserFromRoom")
    public Home removeUserFromRoomByRoomIdAndUserName(@RequestBody Map<String, String> data) {
        String userName = data.get("userName");
        String roomId = data.get("roomId");
        roomService.removeUserFromRoomByRoomIdAndUserName(Integer.parseInt(roomId), userName);
        return Home.getHome();
    }

    @PostMapping("AddUserToRoom")
    public Home addUserToRoomByRoomIdAndUserName(@RequestBody Map<String, String> data) {
        String userName = data.get("userName");
        String roomId = data.get("roomId");
        roomService.addUserToRoomByRoomIdAndUserName(Integer.parseInt(roomId), userName);
        return Home.getHome();
    }
}
