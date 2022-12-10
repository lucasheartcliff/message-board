package com.messageboard.controller;

import com.messageboard.model.User;
import com.messageboard.service.ServiceFactory;
import com.messageboard.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController extends BaseController {

    @Autowired
    public UsersController(ServiceFactory serviceFactory) {
        super(serviceFactory);
    }

    @GetMapping
    public List<User> getUsers() {
        return serviceFactory.buildUserService().getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") Long id) throws Exception {
        return serviceFactory.buildUserService().getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserViewModel model) throws Exception {
        return serviceFactory.buildUserService().createUser(model);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) throws Exception {
        serviceFactory.buildUserService().deleteUser(id);
    }
}
