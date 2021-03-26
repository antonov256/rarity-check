package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.PortfolioApiDto;
import com.atriviss.raritycheck.dto_api.converter.PortfolioApiDtoConverter;
import com.atriviss.raritycheck.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolios")
public class PortfolioRestController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private PortfolioApiDtoConverter converter;

    @GetMapping("/{user_id}")
    public PortfolioApiDto findById(@PathVariable Integer user_id) {
        return converter.convert(portfolioService.findByUserId(user_id));
    }
}
