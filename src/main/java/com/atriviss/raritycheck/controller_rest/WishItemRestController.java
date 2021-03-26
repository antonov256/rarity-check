package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.WishItemApiDto;
import com.atriviss.raritycheck.dto_api.converter.WishItemApiDtoConverter;
import com.atriviss.raritycheck.service.WishItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish_items")
public class WishItemRestController {
    @Autowired
    private WishItemService ownItemService;
    @Autowired
    private WishItemApiDtoConverter converter;

    @GetMapping("/{id}")
    public WishItemApiDto getById(@PathVariable Integer id) {
        return converter.convert(ownItemService.findById(id));
    }
}
