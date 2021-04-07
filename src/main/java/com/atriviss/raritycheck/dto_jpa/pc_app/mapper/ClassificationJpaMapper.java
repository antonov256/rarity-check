package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.CategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.model.Classification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CategoryJpaMapper.class, SubcategoryJpaMapper.class})
public interface ClassificationJpaMapper {
    @Mapping(source = "categoryJpaDto", target = "category")
    @Mapping(source = "subcategoryJpaDto", target = "subcategory")
    Classification toClassification(CategoryJpaDto categoryJpaDto, SubcategoryJpaDto subcategoryJpaDto);

    @Mapping(source = "classification.category", target = ".")
    CategoryJpaDto toCategoryJpaDto(Classification classification);

    @Mapping(source = "classification.subcategory", target = ".")
    SubcategoryJpaDto toSubcategoryJpaDto(Classification classification);
}
