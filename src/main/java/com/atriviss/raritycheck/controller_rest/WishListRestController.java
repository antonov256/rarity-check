package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.dto_api.WishItemApiDto;
import com.atriviss.raritycheck.dto_api.WishListApiDto;
import com.atriviss.raritycheck.dto_api.to_create.WishItemToAdd;
import com.atriviss.raritycheck.dto_api.to_create.WishItemToAddToUser;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class WishListRestController {
    @Autowired
    private WishListService service;

    @Autowired
    private UserFromPrincipalExtractor userExtractor;

    @GetMapping("profile/portfolio/wishItems")
    public CollectionModel<WishItemApiDto> getCurrentUserWishList(Principal principal) {
        User user = userExtractor.extract(principal);

        WishListApiDto wishList = service.wishListForUserId(user.getId());
        return CollectionModel.of(wishList.getItems());
    }

    @PostMapping("profile/portfolio/wishItems")
    public EntityModel<WishItemApiDto> addCurrentUserWishItem(@RequestBody WishItemToAdd wishItemToAdd, Principal principal) {
        User user = userExtractor.extract(principal);

        WishItemToAddToUser wishItemToAddToUser = new WishItemToAddToUser(user.getId(), wishItemToAdd.getItemId());
        WishItemApiDto wishItemApiDto = service.addWishItem(wishItemToAddToUser);

        return EntityModel.of(wishItemApiDto);
    }

    @DeleteMapping("profile/portfolio/wishItems/{wishItemId}")
    public ResponseEntity<?> deleteCurrentUserWishItem(@PathVariable Integer wishItemId, Principal principal) {
        User user = userExtractor.extract(principal);

        service.deleteWishItemById(user.getId(), wishItemId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("users/{userId}/portfolio/wishItems")
    public CollectionModel<WishItemApiDto> getWishList(@PathVariable Integer userId) {
        WishListApiDto wishList = service.wishListForUserId(userId);
        return CollectionModel.of(wishList.getItems());
    }
}
