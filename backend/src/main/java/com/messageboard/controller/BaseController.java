package com.messageboard.controller;

import com.messageboard.service.ServiceFactory;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseController {
    protected final ServiceFactory serviceFactory;
    protected BaseController(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
