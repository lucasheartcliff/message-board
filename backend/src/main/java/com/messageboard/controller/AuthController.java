package com.messageboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageboard.RequestHandler;
import com.messageboard.model.User;
import com.messageboard.viewmodel.UserViewModel;

@RestController
@RequestMapping("/auth/login")
public class AuthController extends BaseController {

  @Autowired
  public AuthController(RequestHandler requestHandler) {
    super(requestHandler);
  }

  @PostMapping
  public User authUser(@RequestBody UserViewModel model) throws Exception {
    return encapsulateRequest((serviceFactory) -> {
      return serviceFactory.buildUserService().getOrCreateUser(model);
    });
  }
}
