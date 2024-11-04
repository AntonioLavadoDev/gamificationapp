package com.alav.gamificationapp.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alav.gamificationapp.model.entity.User;
import com.alav.gamificationapp.repository.UserRepository;


@Service
@org.springframework.transaction.annotation.Transactional
public class UserService {

    private final UserRepository userRepository;

    //auto-wired constructor, to inject the userRepository
    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //This method is used to create a new user
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        //setting the default values for the user
        user.setCurrentLevel(1);
        user.setTotalPoints(0);
        return userRepository.save(user);
    }

    //This method is used to get all the users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //This method is used to get the user  by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //This method is used to update the user details
    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for this id :: " + id));
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    //This method is used to delete the user by id
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //This method is used to add points to the user
    public User addPoints(Long userId, Integer points){
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));

        user.setTotalPoints(user.getTotalPoints()+points);
        //checking if the user has reached the next level or logic to increase the level

        return userRepository.save(user);
    }

    public User levelUp(Long userId){
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));

        user.setCurrentLevel(user.getCurrentLevel()+1);
        return userRepository.save(user);
    }



}
