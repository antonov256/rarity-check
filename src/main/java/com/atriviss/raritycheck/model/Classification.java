package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Classification {
    public static Classification NO_CLASSIFICATION = new Classification(Category.NO_CATEGORY, Subcategory.NO_SUBCATEGORY);

    private final Category category;
    private final Subcategory subcategory;

    public Classification(Category category, Subcategory subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }
}
