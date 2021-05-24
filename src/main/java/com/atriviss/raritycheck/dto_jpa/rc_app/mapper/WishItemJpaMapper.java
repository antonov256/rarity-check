package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.WishItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.rc_app.WishItemJpaDto;
import com.atriviss.raritycheck.model.WishItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ItemJpaMapper.class)
public interface WishItemJpaMapper {
    WishItem toWishItem(WishItemJpaDto wishItemJpaDto);

    WishItemJpaDto toWishItemJpaDto(WishItem wishItem);

    WishItemJpaDto toWishItemJpaDto(WishItemToAddToUser wishItemToAddToUser);

    List<WishItem> toWishItemList(List<WishItemJpaDto> wishItemJpaDtoList);

    List<WishItemJpaDto> toWishItemJpaDtoList(List<WishItem> wishItemList);
}
