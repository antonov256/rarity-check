package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.dto_api.OwnListApiDto;
import com.atriviss.raritycheck.dto_api.to_create.OwnItemToAdd;
import com.atriviss.raritycheck.dto_api.to_create.OwnItemToAddToUser;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.OwnListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class OwnListRestController {
    @Autowired
    private OwnListService service;

    @Autowired
    private UserFromPrincipalExtractor userExtractor;

    @GetMapping("profile/portfolio/ownItems")
    public CollectionModel<OwnItemApiDto> getCurrentUserOwnList(Principal principal) {
        User user = userExtractor.extract(principal);

        OwnListApiDto ownList = service.ownListForUserId(user.getId());
        return CollectionModel.of(ownList.getItems());
    }

    @PostMapping("profile/portfolio/ownItems")
    public EntityModel<OwnItemApiDto> addCurrentUserOwnItem(@RequestBody OwnItemToAdd ownItemToAdd, Principal principal) {
        User user = userExtractor.extract(principal);

        OwnItemToAddToUser ownItemToAddToUser = new OwnItemToAddToUser(user.getId(), ownItemToAdd.getItemId());
        OwnItemApiDto ownItemApiDto = service.addOwnItem(ownItemToAddToUser);

        return EntityModel.of(ownItemApiDto);
    }

    @DeleteMapping("profile/portfolio/ownItems/{ownItemId}")
    public ResponseEntity<?> deleteCurrentUserOwnItem(@PathVariable Integer ownItemId, Principal principal) {
        User user = userExtractor.extract(principal);

        service.deleteOwnItemById(user.getId(), ownItemId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("users/{userId}/portfolio/ownItems")
    public CollectionModel<OwnItemApiDto> getUserOwnList(@PathVariable Integer userId) {
        OwnListApiDto ownList = service.ownListForUserId(userId);
        return CollectionModel.of(ownList.getItems());
    }
}
