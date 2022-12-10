package com.avaliation.poo.controller;

import com.avaliation.poo.model.User;
import com.avaliation.poo.service.ServiceFactory;
import com.avaliation.poo.service.user.UserServiceImpl;
import com.avaliation.poo.viewmodel.UserViewModel;
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
