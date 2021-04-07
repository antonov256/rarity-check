package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryApiMapper {
    Category toCategory(CategoryApiDto categoryApiDto);

    CategoryApiDto toCategoryApiDto(Category category);

    List<Category> toCategoryList(List<CategoryApiDto> categoryApiDtos);

    List<CategoryApiDto> toCategoryApiDtoList(List<Category> categories);
}
