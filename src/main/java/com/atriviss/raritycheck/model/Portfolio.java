package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Portfolio {
    private final String portfolioDescription;
    private final OwnList ownList;
    private final WishList wishList;

    public Portfolio(String portfolioDescription) {
        this.portfolioDescription = portfolioDescription;
        this.ownList = new OwnList();
        this.wishList = new WishList();
    }

    public Portfolio(String portfolioDescription, OwnList ownList, WishList wishList) {
        this.portfolioDescription = portfolioDescription;
        this.ownList = ownList;
        this.wishList = wishList;
    }
}
