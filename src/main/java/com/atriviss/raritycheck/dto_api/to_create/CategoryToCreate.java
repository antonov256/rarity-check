package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryToCreate {
    private String name;
    private List<SubcategoryToCreate> subcategories;
}
