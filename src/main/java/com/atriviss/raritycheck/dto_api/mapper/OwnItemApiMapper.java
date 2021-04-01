package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.model.OwnItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        uses = ItemApiMapper.class,
        imports = OwnItem.class
)
public interface OwnItemApiMapper {
    OwnItem toOwnItem(OwnItemApiDto ownItemApiDto);

    OwnItemApiDto toOwnItemApiDto(OwnItem ownItem);

    List<OwnItem> toOwnItemList(List<OwnItemApiDto> ownItemApiDtos);

    List<OwnItemApiDto> toOwnItemApiDtoList(List<OwnItem> ownItems);
}
