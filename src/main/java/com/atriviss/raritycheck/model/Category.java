package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Category {
    private final Integer id;
    private final String name;
    private final List<Subcategory> subcategories;

    public Category(Integer id, String name, List<Subcategory> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }
}
