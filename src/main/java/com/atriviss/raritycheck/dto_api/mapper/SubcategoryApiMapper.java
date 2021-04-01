package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import com.atriviss.raritycheck.model.Subcategory;
import org.mapstruct.Mapper;

@Mapper
public interface SubcategoryApiMapper {
    Subcategory toSubcategory(SubcategoryApiDto subcategoryApiDto);

    SubcategoryApiDto toSubcategoryApiDto(Subcategory subcategory);
}
