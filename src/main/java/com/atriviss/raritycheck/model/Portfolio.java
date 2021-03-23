package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Portfolio {
    private List<OwnItem> ownList;
    private List<WishItem> wishList;

    public Portfolio() {
        this.ownList = new ArrayList<>();
        this.wishList = new ArrayList<>();
    }

    public Portfolio(List<OwnItem> ownList, List<WishItem> wishList) {
        this.ownList = ownList;
        this.wishList = wishList;
    }

    public void addOwnItem(OwnItem ownItem) {
        ownList.add(ownItem);
    }

    public void removeOwnItem(OwnItem ownItem) {
        ownList.remove(ownItem);
    }

    public void addWishItem(WishItem wishItem) {
        wishList.add(wishItem);
    }

    public void removeWishItem(WishItem wishItem) {
        wishList.remove(wishItem);
    }
}
