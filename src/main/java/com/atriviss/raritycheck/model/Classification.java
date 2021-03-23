package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Classification {
    private final Category category;
    private final Optional<Subcategory> subcategory;

    public Classification(Category category) {
        this.category = category;
        subcategory = Optional.empty();
    }

    public Classification(Subcategory subcategory) {
        this.category = subcategory.getCategory();
        this.subcategory = Optional.of(subcategory);
    }

    public boolean subcategoryPresented() {
        return subcategory.isPresent();
    }
}
