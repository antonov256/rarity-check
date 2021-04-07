package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public EntityModel<UserApiDto> getUserById(@PathVariable Integer id) {
        UserApiDto apiDto = userService.getUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with id=%s not found", id)
                ));

        return EntityModel.of(apiDto);
    }
}
