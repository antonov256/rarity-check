package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "subcategory", itemRelation = "subcategories")
public class SubcategoryApiDto {
    private Integer id;
    private Integer categoryId;
    private String name;

    public SubcategoryApiDto(Integer id, Integer categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
