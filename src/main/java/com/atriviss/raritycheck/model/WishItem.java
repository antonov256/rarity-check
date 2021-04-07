package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class WishItem {
    private final Integer id;
    private final Integer userId;
    private final Item item;

    public WishItem(Integer id, Integer userId, Item item) {
        this.id = id;
        this.userId = userId;
        this.item = item;
    }
}
