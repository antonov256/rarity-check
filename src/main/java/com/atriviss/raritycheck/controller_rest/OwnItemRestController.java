package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.dto_api.converter.OwnItemApiDtoConverter;
import com.atriviss.raritycheck.service.OwnItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/own_items")
public class OwnItemRestController {
    @Autowired
    private OwnItemService ownItemService;
    @Autowired
    private OwnItemApiDtoConverter converter;

    @GetMapping("/{id}")
    public OwnItemApiDto getById(@PathVariable Integer id) {
        return converter.convert(ownItemService.findById(id));
    }
}
