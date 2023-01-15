package com.messageboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageboard.RequestHandler;
import com.messageboard.model.User;
import com.messageboard.viewmodel.UserViewModel;

@RestController
@RequestMapping("/api/users")
public class UsersController extends BaseController {

  @Autowired
  public UsersController(RequestHandler requestHandler) {
    super(requestHandler);
  }

  @GetMapping
  public List<User> getUsers() {
    return encapsulateRequest((serviceFactory) -> {
      return serviceFactory.buildUserService().getUsers();
    });
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable(value = "id") Long id) throws Exception {
    return encapsulateRequest((serviceFactory) -> {
      return serviceFactory.buildUserService().getUser(id);
    });
  }

  @PostMapping
  public User createUser(@RequestBody UserViewModel model) throws Exception {
    return encapsulateRequest((serviceFactory) -> {
      return serviceFactory.buildUserService().createUser(model);
    });
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable(value = "id") Long id) throws Exception {
    encapsulateRequest((serviceFactory) -> {
      serviceFactory.buildUserService().deleteUser(id);
    });
  }
}
