package com.example.fullStackbackEnd.controller;
import com.example.fullStackbackEnd.exception.UserNotFoundException;
import com.example.fullStackbackEnd.model.Branch;
import com.example.fullStackbackEnd.model.User;
import com.example.fullStackbackEnd.model.UserDTO;
import com.example.fullStackbackEnd.repository.BranchRepository;
import com.example.fullStackbackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @PostMapping("/user")
    User newUser(@RequestBody UserDTO newUser){
        System.out.println(newUser.toString());
        Optional<Branch> branch = branchRepository.findById(Long.valueOf(newUser.getBranch_id()));
        User user = new User(null, newUser.getUsername(), branch.get(), newUser.getName(), newUser.getEmail());
        return userRepository.save(user);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @GetMapping("/user/{id}")
    User getUserById(@PathVariable  Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody UserDTO newUser, @PathVariable Long id){
        Optional<Branch> branch = branchRepository.findById(Long.valueOf(newUser.getBranch_id()));
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setBranch(branch.get());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException((id));
        }
        userRepository.deleteById(id);
        return "User with id" +id+"has been deleted successfully";
    }
}
