package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.WishItemApiDto;
import com.atriviss.raritycheck.dto_api.WishListApiDto;
import com.atriviss.raritycheck.model.WishItem;
import com.atriviss.raritycheck.model.WishList;
import org.mapstruct.Mapper;

@Mapper(
        uses = WishItemApiMapper.class,
        imports = {WishItem.class, WishItemApiDto.class}
)
public interface WishListApiMapper {
    WishList toWishList(WishListApiDto wishListApiDto);

    WishListApiDto toWishListApiDto(WishList wishList);
}
