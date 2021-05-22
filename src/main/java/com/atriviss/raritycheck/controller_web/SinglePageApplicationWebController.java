package com.atriviss.raritycheck.controller_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageApplicationWebController {
    @GetMapping({
            "about/**",
            "admin/**",
            "auth/**",
            "category/**",
            "home/**",
            "item/**",
            "portfolio/**",
            "profile/**",
            "subcategory/**"
    })
    public String allPages() {
        return "forward:/";
    }
}
