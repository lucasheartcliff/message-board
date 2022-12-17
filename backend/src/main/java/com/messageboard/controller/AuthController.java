package com.messageboard.controller;

import com.messageboard.RequestHandler;
import com.messageboard.model.User;
import com.messageboard.viewmodel.UserViewModel;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


@Path("/auth/login")
public class AuthController extends BaseController {
    public AuthController(RequestHandler requestHandler) {
        super(requestHandler);
    }

    @POST
    public User authUser( UserViewModel model) throws Exception {
        return encapsulateRequest((serviceFactory) -> {
            return serviceFactory.buildUserService().getOrCreateUser(model);
        });
    }
}
