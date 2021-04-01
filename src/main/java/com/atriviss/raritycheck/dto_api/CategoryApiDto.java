package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class CategoryApiDto {
    private final Integer id;
    private final String name;

    public CategoryApiDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
