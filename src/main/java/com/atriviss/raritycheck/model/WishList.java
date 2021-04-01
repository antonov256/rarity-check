package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.List;

@Getter
public class WishList {
    private final List<WishItem> items;

    public WishList(List<WishItem> items) {
        this.items = items;
    }

    public Integer size() {
        return items.size();
    }
}
