package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.CategoryJpaDto;
import com.atriviss.raritycheck.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryJpaMapper {
    Category toCategory(CategoryJpaDto categoryJpaDto);

    CategoryJpaDto toCategoryJpaDto(Category category);
}
