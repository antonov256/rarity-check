package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "classification", itemRelation = "classification")
public class ClassificationApiDto {
    private CategoryApiDto category;
    private SubcategoryApiDto subcategory;

    public ClassificationApiDto(CategoryApiDto category, SubcategoryApiDto subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }
}
