package dev.TeamRedDragon.SmartHomeSimulator.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/AddUser")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "User is sucessfully added!";
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/GetUserByUserName")
    public User getUserByUserName(@RequestBody Map<String, String> data){
        String userName = data.get("userName");
        return userService.getUserByUserName(userName);
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
            userMap.put("location", user.getLocation());

            return ResponseEntity.ok(userMap);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials. ");
        }
    }

    @PatchMapping("/UpdateUser")
    public ResponseEntity<Object> UpdateUser(@RequestBody User user) {

        if (userService.getUserById(user.getId()) != null)
        {
            userService.updateUser(user);
            return  ResponseEntity.status(HttpStatus.OK).body("User updated. ");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    @DeleteMapping("/DeleteUser")
    public ResponseEntity<Object> DeleteUserById(@RequestBody Map<String, String> data) {
        String Id = data.get("id");
        if (userService.getUserById(Integer.parseInt(Id)) != null)
        {
            userService.deleteUser(Integer.parseInt(Id));
            return ResponseEntity.status(HttpStatus.OK).body("User deleted. ");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");

    }
}
