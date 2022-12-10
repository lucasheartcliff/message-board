package com.avaliation.poo.controller;

import com.avaliation.poo.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseController {
    protected final ServiceFactory serviceFactory;
    protected BaseController(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
