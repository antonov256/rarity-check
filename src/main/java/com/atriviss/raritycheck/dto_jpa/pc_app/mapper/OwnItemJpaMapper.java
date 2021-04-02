package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.OwnItemJpaDto;
import com.atriviss.raritycheck.model.OwnItem;
import org.mapstruct.Mapper;

@Mapper(uses = ItemJpaMapper.class)
public interface OwnItemJpaMapper {
    OwnItem toOwnItem(OwnItemJpaDto ownItemJpaDto);

    OwnItemJpaDto toOwnItemApiDto(OwnItem ownItem);
}
