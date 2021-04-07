package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class OwnItem {
    private final Integer id;
    private final Integer userId;
    private final Item item;

    public OwnItem(Integer id, Integer userId, Item item) {
        this.id = id;
        this.userId = userId;
        this.item = item;
    }
}
