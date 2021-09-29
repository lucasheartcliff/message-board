package com.avaliation.poo.controller;

import com.avaliation.poo.model.User;
import com.avaliation.poo.service.UserService;
import com.avaliation.poo.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    public UsersController() {
        userService = new UserService();
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody UserViewModel model) throws Exception {
        return userService.createUser(model);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) throws Exception {
        userService.deleteUser(id);
    }
}
