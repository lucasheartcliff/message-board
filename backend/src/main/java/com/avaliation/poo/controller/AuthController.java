package com.avaliation.poo.controller;

import com.avaliation.poo.model.User;
import com.avaliation.poo.service.BaseService;
import com.avaliation.poo.service.UserService;
import com.avaliation.poo.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/login")
public class AuthController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User authUser(@RequestBody UserViewModel model) throws Exception {
        return userService.getOrCreateUser(model);
    }
}
