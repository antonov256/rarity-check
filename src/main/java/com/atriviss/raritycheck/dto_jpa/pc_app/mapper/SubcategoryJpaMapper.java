package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.model.Subcategory;
import org.mapstruct.Mapper;

@Mapper
public interface SubcategoryJpaMapper {
    Subcategory toSubcategory(SubcategoryJpaDto subcategoryJpaDto);

    SubcategoryJpaDto toSubcategoryJpaDto(Subcategory subcategory);
}
