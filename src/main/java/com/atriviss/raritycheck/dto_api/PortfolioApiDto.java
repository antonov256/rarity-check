package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class PortfolioApiDto {
    private final String portfolioDescription;
    private final OwnListApiDto ownList;
    private final WishListApiDto wishList;

    public PortfolioApiDto(String portfolioDescription, OwnListApiDto ownList, WishListApiDto wishList) {
        this.portfolioDescription = portfolioDescription;
        this.ownList = ownList;
        this.wishList = wishList;
    }
}
