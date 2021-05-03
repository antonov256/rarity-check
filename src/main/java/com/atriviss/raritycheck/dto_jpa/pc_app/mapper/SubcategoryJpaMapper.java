package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.model.Subcategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SubcategoryJpaMapper {
    Subcategory toSubcategory(SubcategoryJpaDto subcategoryJpaDto);

    SubcategoryJpaDto toSubcategoryJpaDto(Subcategory subcategory);

    List<Subcategory> toSubcategoryList(List<SubcategoryJpaDto> subcategoryJpaDtoList);

    List<SubcategoryJpaDto> toSubcategoryJpaDtoList(List<Subcategory> subcategoryList);
}
