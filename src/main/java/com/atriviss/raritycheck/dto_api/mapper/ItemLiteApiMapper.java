package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.ItemLiteApiDto;
import com.atriviss.raritycheck.model.ItemLite;
import org.mapstruct.Mapper;

@Mapper(uses = {ClassificationApiMapper.class})
public interface ItemLiteApiMapper {
    ItemLite toItemLite(ItemLiteApiDto itemLiteApiDto);

    ItemLiteApiDto toItemLiteApiDto(ItemLite itemLite);
}
