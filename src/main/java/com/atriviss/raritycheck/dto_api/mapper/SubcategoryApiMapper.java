package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import com.atriviss.raritycheck.model.Subcategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SubcategoryApiMapper {
    Subcategory toSubcategory(SubcategoryApiDto subcategoryApiDto);

    SubcategoryApiDto toSubcategoryApiDto(Subcategory subcategory);

    List<SubcategoryApiDto> toSubcategoryApiDtoList(List<Subcategory> subcategoryList);

    List<Subcategory> toSubcategoryList(List<SubcategoryApiDto> subcategoryApiDtoList);
}
