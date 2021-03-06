package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.CategoryToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.CategoryJpaDto;
import com.atriviss.raritycheck.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryJpaMapper {
    Category toCategory(CategoryJpaDto categoryJpaDto);

    CategoryJpaDto toCategoryJpaDto(Category category);

    CategoryJpaDto toCategoryJpaDto(CategoryToCreate categoryToCreate);

    List<Category> toCategoryList(List<CategoryJpaDto> categoryJpaDtos);

    List<CategoryJpaDto> toCategoryJpaDtoList(List<Category> categories);
}
