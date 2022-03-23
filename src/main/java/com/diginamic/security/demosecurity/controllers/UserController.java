package com.diginamic.security.demosecurity.controllers;

import com.diginamic.security.demosecurity.entities.User;
import com.diginamic.security.demosecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {
    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public String findAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "usersList";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow());
        return "edit";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
