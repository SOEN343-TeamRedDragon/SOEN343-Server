package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;
    private User user;

    @BeforeEach
    void setUp() {
        room = new Room(1, 1, "Living Room", new ArrayList<>(), new ArrayList<>());
        user = new User(1, "John Doe", "johndoe", "password", "user", "Living Room");
    }

    @Test
    void testAddUserToRoom() {
        room.addUserToRoom(user);
        assertEquals(1, room.getUserList().size());
        assertTrue(room.getUserList().contains(user));
    }

    @Test
    void testRemoveUserFromRoom() {
        room.addUserToRoom(user);
        room.removeUserFromRoom(user);
        assertEquals(0, room.getUserList().size());
        assertFalse(room.getUserList().contains(user));
    }

    @Test
    void testAutoModeLightAdjustment() {
        Light light = new Light(31, "Light", false);
        room.getSmartElementList().add(light);
        room.setAutoModeEnabled(true);

        room.addUserToRoom(user);
        assertTrue(light.getIsOpen());

        room.removeUserFromRoom(user);
        assertFalse(light.getIsOpen());
    }
}