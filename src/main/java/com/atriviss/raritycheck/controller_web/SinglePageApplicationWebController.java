package com.atriviss.raritycheck.controller_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SinglePageApplicationWebController {
    @RequestMapping(value = "/{path:[^.]*}")
    public String otherPages() {
        return "index.html";
    }

    @RequestMapping("/")
    public String home() {
        return "index.html";
    }
}
