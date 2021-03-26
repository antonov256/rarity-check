package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.UserInfoApiDto;
import com.atriviss.raritycheck.dto_api.converter.UserInfoApiDtoConverter;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoApiDtoConverter converter;

    @GetMapping
    public List<UserInfoApiDto> all() {
        return userService.all().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserInfoApiDto findById(@PathVariable Integer id) {
        return converter.convert(userService.findById(id));
    }
}
