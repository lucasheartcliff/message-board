package com.messageboard.controller;

import com.messageboard.model.User;
import com.messageboard.service.ServiceFactory;
import com.messageboard.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/login")
public class AuthController extends BaseController {

    @Autowired
    public AuthController(ServiceFactory serviceFactory) {
        super(serviceFactory);
    }

    @PostMapping
    public User authUser(@RequestBody UserViewModel model) throws Exception {
        return serviceFactory.buildUserService().getOrCreateUser(model);
    }
}
