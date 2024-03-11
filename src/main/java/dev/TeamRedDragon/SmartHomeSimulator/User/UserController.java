package dev.TeamRedDragon.SmartHomeSimulator.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/AddUser")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "User is sucessfully added!";
    }

    // Login function to authenticate the user.
    @PostMapping("/AuthenticateUser")
    public ResponseEntity<Object> AuthenticateUser(@RequestBody Map<String, String> credentials) {
        String userName = credentials.get("userName");
        String password = credentials.get("password");

        Optional<User> userOptional = userService.authenticateUser(userName, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Create a map to hold user information
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", user.getId());
            userMap.put("userName", user.getUserName());
            userMap.put("role", user.getRole());

            return ResponseEntity.ok(userMap);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials. ");
        }
    }



}
