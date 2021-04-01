package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.List;

@Getter
public class OwnList {
    private final List<OwnItem> items;

    public OwnList(List<OwnItem> items) {
        this.items = items;
    }

    public Integer size() {
        return items.size();
    }
}
