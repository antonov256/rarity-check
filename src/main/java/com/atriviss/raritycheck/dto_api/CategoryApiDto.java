package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryApiDto {
    private Integer id;
    private String name;

    public CategoryApiDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
