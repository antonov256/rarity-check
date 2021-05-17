package com.atriviss.raritycheck.controller_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SinglePageApplicationWebController {
    @GetMapping("/**/{path:[^\\.]*}/**")
    public String allPages(@PathVariable String path) {
        return "forward:/";
    }
}
