package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Subcategory {
    private final Integer id;
    private final String name;
    private final Category category;

    public Subcategory(Integer id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
