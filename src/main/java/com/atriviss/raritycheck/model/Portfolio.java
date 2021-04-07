package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Portfolio {
    private final OwnList ownList;
    private final WishList wishList;

    public Portfolio(OwnList ownList, WishList wishList) {
        this.ownList = ownList;
        this.wishList = wishList;
    }
}
