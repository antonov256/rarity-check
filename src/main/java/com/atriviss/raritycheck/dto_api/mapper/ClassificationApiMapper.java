package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.dto_api.ClassificationApiDto;
import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import com.atriviss.raritycheck.model.Classification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CategoryApiMapper.class, SubcategoryApiMapper.class})
public interface ClassificationApiMapper {
    Classification toClassification(ClassificationApiDto classificationApiDto);

    ClassificationApiDto toClassificationApiDto(Classification classification);

    @Mapping(source = "categoryApiDto", target = "category")
    @Mapping(source = "subcategoryApiDto", target = "subcategory")
    Classification toClassification(CategoryApiDto categoryApiDto, SubcategoryApiDto subcategoryApiDto);

    @Mapping(source = "classification.category", target = ".")
    CategoryApiDto toCategoryApiDto(Classification classification);

    @Mapping(source = "classification.subcategory", target = ".")
    SubcategoryApiDto toSubcategoryApiDto(Classification classification);
}
