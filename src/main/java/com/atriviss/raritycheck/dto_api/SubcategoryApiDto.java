package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class SubcategoryApiDto {
    private final Integer id;
    private final Integer categoryId;
    private final String name;

    public SubcategoryApiDto(Integer id, Integer categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
