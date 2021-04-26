package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.OwnItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.pc_app.OwnItemJpaDto;
import com.atriviss.raritycheck.model.OwnItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ItemJpaMapper.class)
public interface OwnItemJpaMapper {
    OwnItem toOwnItem(OwnItemJpaDto ownItemJpaDto);

    OwnItemJpaDto toOwnItemJpaDto(OwnItem ownItem);

    OwnItemJpaDto toOwnItemJpaDto(OwnItemToAddToUser ownItemToAddToUser);

    List<OwnItem> toOwnItemList(List<OwnItemJpaDto> ownItemJpaDtoList);

    List<OwnItemJpaDto> toOwnItemJpaDtoList(List<OwnItem> ownItemList);
}
