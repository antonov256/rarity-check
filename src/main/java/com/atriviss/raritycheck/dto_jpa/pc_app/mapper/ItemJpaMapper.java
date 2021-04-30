package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.model.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        uses = {CategoryJpaMapper.class, SubcategoryJpaMapper.class, PhotoJpaMapper.class, VideoJpaMapper.class},
        imports = {Photo.class, Video.class}
)
public interface ItemJpaMapper {
    Item toItem(ItemJpaDto itemJpaDto);

    ItemJpaDto toItemJpaDto(Item item);

    @Mapping(source = "category", target = "classification.category")
    @Mapping(source = "subcategory", target = "classification.subcategory")
    List<Item> toItemList(List<ItemJpaDto> itemJpaDtos);

    @Mapping(source = "classification.category", target = "category")
    @Mapping(source = "classification.subcategory", target = "subcategory")
    List<ItemJpaDto> toItemJpaDtoList(List<Item> items);
}
