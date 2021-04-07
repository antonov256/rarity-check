package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.WishItemJpaDto;
import com.atriviss.raritycheck.model.WishItem;
import org.mapstruct.Mapper;

@Mapper(uses = ItemJpaMapper.class)
public interface WishItemJpaMapper {
    WishItem toWishItem(WishItemJpaDto wishItemJpaDto);

    WishItemJpaDto toWishItemJpaDto(WishItem wishItem);
}
