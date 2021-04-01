package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryApiMapper {
    Category toCategory(CategoryApiDto categoryApiDto);

    CategoryApiDto toCategoryApiDto(Category category);
}
