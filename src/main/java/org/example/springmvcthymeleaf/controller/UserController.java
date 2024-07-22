package org.example.springmvcthymeleaf.controller;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.example.springmvcthymeleaf.model.User;
import org.example.springmvcthymeleaf.service.UserService;

import java.util.List;

@Controller
@Log
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String findAll(@NonNull Model model) {
        log.info("findAll: List<User> users = userService.findAll()");
        List<User> users = userService.findAll();
        log.info("findAll: users.size() = " + users.size());
        log.info("findAll: model.addAttribute() - Start");
        model.addAttribute("users", users);
        log.info("findAll: model.addAttribute() - Successful");
        log.info("findAll: Try user-list.html");
        return "user-list";
    }

    @PostMapping("/users")
    public String addUser(@NonNull User user, @NonNull Model model) {
        log.info("addUser: User = " + user.toString());
        log.info("addUser: userService.saveUser(user) - Start");
        userService.saveUser(user);
        log.info("addUser: userService.saveUser(user) - Successful");
        model.addAttribute("users", userService.findAll());
        log.info("addUser: Try user-list.html");
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("createUserForm: - Start");
        log.info("createUserForm: Try user-create.html");
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@NonNull User user) {
        log.info("createUser: User = " + user.toString());
        log.info("createUser: userService.saveUser(user) - Start");
        userService.saveUser(user);
        log.info("createUser: userService.saveUser(user) - Successful");
        log.info("createUser: Try redirect:/users");
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        log.info("deleteUser: userService.deleteById(id) - Start");
        userService.deleteById(id);
        log.info("deleteUser: userService.deleteById(id) - Successful");
        log.info("deleteUser: Try redirect:/users");
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String getOne(@PathVariable("id") int id, @NonNull Model model) {
        log.info("getOne: Id = " + id);
        log.info("getOne: User user = userService.getOne(id) - Start");
        User user = userService.getOne(id);
        log.info("getOne: User user = userService.getOne(id) - Successful");
        log.info("getOne: User = " + user.toString());
        model.addAttribute("user", user);
        log.info("getOne: Try user-update.html");
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@NonNull User user) {
        log.info("updateUser: User = " + user.toString());
        log.info("updateUser: userService.updateUser(user) - Start");
        userService.updateUser(user);
        log.info("updateUser: userService.updateUser(user) - Successful");
        log.info("updateUser: Try redirect:/users");
        return "redirect:/users";
    }
}