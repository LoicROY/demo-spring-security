package com.diginamic.security.demosecurity.controllers;

import com.diginamic.security.demosecurity.entities.User;
import com.diginamic.security.demosecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserControllerRest {
    UserRepository userRepository;

    @Autowired
    public UserControllerRest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public User saevUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public User updateUser(@RequestParam Long id) {
        return userRepository.save(userRepository.findById(id).orElseThrow());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
    }
}
