package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.model.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {CategoryJpaMapper.class, SubcategoryJpaMapper.class, PhotoJpaMapper.class, VideoJpaMapper.class},
        imports = {Photo.class, Video.class}
)
public interface ItemJpaMapper {
    @Mapping(source = "category", target = "classification.category")
    @Mapping(source = "subcategory", target = "classification.subcategory")
    Item toItem(ItemJpaDto itemJpaDto);

    @Mapping(source = "classification.category", target = "category")
    @Mapping(source = "classification.subcategory", target = "subcategory")
    ItemJpaDto toItemJpaDto(Item item);
}
