package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Category {
    public static final Category NO_CATEGORY = new Category(0, "no category", Collections.emptyList());

    private final Integer id;
    private final String name;
    private List<Subcategory> subcategories;

    public Category(Integer id, String name, List<Subcategory> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }
}
