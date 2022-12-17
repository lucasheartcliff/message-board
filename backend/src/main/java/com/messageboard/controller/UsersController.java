package com.messageboard.controller;

import com.messageboard.RequestHandler;
import com.messageboard.model.User;
import com.messageboard.viewmodel.UserViewModel;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/api/users")
public class UsersController extends BaseController {
    public UsersController(RequestHandler requestHandler) {
        super(requestHandler);
    }

    @GET
    public List<User> getUsers() {
        return encapsulateRequest((serviceFactory) -> {
            return serviceFactory.buildUserService().getUsers();
        });
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") Long id) throws Exception {
        return encapsulateRequest((serviceFactory) -> {
            return serviceFactory.buildUserService().getUser(id);
        });
    }

    @POST
    public User createUser(UserViewModel model) throws Exception {
        return encapsulateRequest((serviceFactory) -> {
            return serviceFactory.buildUserService().createUser(model);
        });
    }

    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam(value = "id") Long id) throws Exception {
        encapsulateRequest((serviceFactory) -> {
            serviceFactory.buildUserService().deleteUser(id);
        });
    }
}
