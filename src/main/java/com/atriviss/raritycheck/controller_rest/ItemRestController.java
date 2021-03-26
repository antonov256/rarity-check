package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.converter.ItemApiDtoConverter;
import com.atriviss.raritycheck.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemRestController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemApiDtoConverter converter;

    @GetMapping("/{id}")
    public ItemApiDto getById(@PathVariable Integer id) {
        return converter.convert(itemService.findById(id));
    }
}
