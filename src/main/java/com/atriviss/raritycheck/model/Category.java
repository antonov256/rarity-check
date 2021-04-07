package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Category {
    public static final Category NO_CATEGORY = new Category(0, "no category");

    private final Integer id;
    private final String name;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
