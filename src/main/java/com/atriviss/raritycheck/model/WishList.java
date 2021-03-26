package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WishList {
    private final List<WishItem> items;

    public WishList() {
        this.items = new ArrayList<>();
    }

    public WishList(List<WishItem> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }

    public void addItem(WishItem item) {
        items.add(item);
    }

    public void removeItem(WishItem item) {
        items.remove(item);
    }
}
