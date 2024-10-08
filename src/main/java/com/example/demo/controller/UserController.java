package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userForm")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        userService.saveUser(user);
        // Redirect to the login page after successful user registration
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSuccess(User user, Model model) {
        if (userService.authenticateUser(user.getUsername(), user.getPassword())) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "login";
        }
    }


    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    // Read-only REST endpoint to get all users
    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

