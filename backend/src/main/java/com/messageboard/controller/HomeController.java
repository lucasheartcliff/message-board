package com.messageboard.controller;

import com.messageboard.RequestHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/")
public class HomeController extends BaseController {
    public HomeController(RequestHandler requestHandler) {
        super(requestHandler);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "index";
    }
}
