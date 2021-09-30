package com.avaliation.poo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(value = "/")
public class HomeController extends BaseController{
    public String index() {
        return "index";
    }
}
