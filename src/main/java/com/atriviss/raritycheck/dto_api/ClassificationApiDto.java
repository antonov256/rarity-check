package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class ClassificationApiDto {
    private final CategoryApiDto category;
    private final SubcategoryApiDto subcategory;

    public ClassificationApiDto(CategoryApiDto category, SubcategoryApiDto subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }
}
