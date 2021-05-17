package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "category", itemRelation = "categories")
public class CategoryApiDto {
    private Integer id;
    private String name;
    private List<SubcategoryApiDto> subcategories;

    public CategoryApiDto(Integer id, String name, List<SubcategoryApiDto> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }
}
