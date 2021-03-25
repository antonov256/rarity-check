package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class WishItem {
    private final int id;
    private final Item item;
    private final Date addDate;

    public WishItem(int id, Item item, Date addDate) {
        this.id = id;
        this.item = item;
        this.addDate = addDate;
    }
}
