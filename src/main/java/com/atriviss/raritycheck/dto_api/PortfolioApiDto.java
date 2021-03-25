package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class PortfolioApiDto {
    private final OwnListApiDto ownList;
    private final WishListApiDto wishList;

    public PortfolioApiDto(OwnListApiDto ownList, WishListApiDto wishList) {
        this.ownList = ownList;
        this.wishList = wishList;
    }
}
