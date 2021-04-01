package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Subcategory {
    public static final Subcategory NO_SUBCATEGORY = new Subcategory(0, Category.NO_CATEGORY.getId(),"no subcategory");

    private final Integer id;
    private final Integer categoryId;
    private final String name;

    public Subcategory(Integer id, Integer categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
