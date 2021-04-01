package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.WishItemApiDto;
import com.atriviss.raritycheck.model.WishItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ItemApiMapper.class)
public interface WishItemApiMapper {
    WishItem toWishItem(WishItemApiDto wishItemApiDto);

    WishItemApiDto toWishItemApiDto(WishItem wishItem);

    List<WishItem> toWishItemList(List<WishItemApiDto> wishItemApiDtos);

    List<WishItemApiDto> toWishItemApiDtoList(List<WishItem> WishItems);
}
