package com.messageboard.controller;

import com.messageboard.RequestHandler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;


@Path("/")
public class HomeController extends BaseController {
    public HomeController(RequestHandler requestHandler) {
        super(requestHandler);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index(@QueryParam("path") String path) throws IOException {
        Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources(path);

        return path+": " + Collections.list(resources).toString();

    }
}
