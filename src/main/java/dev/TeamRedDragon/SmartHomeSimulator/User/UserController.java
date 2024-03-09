package dev.TeamRedDragon.SmartHomeSimulator.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
