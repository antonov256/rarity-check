package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.List;

@Getter
public class OwnList {
    private final List<OwnItem> items;

    public OwnList(List<OwnItem> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }

    public void addItem(OwnItem item) {
        items.add(item);
    }

    public void removeItem(OwnItem item) {
        items.remove(item);
    }
}
