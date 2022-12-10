package com.avaliation.poo.controller;

import com.avaliation.poo.service.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(value = "/")
public class HomeController extends BaseController{
    public HomeController(ServiceFactory serviceFactory) {
        super(serviceFactory);
    }

    public String index() {
        return "index";
    }
}
