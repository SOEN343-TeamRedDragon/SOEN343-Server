package dev.TeamRedDragon.SmartHomeSimulator.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(Integer id){
        userRepository.deleteById(id);
        return "User is removed!";
    }

    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null){
            return null;
        }
        existingUser.setName(user.getName());
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    public boolean authenticateUser(String userName, String password) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isPresent()){
            System.out.print(userOptional);
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
            return false;
    }
}
