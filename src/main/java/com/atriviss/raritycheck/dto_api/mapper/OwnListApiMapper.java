package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.dto_api.OwnListApiDto;
import com.atriviss.raritycheck.model.OwnItem;
import com.atriviss.raritycheck.model.OwnList;
import org.mapstruct.Mapper;

@Mapper(
        uses = OwnItemApiMapper.class,
        imports = {OwnItem.class, OwnItemApiDto.class}
)
public interface OwnListApiMapper {
    OwnList toOwnList(OwnListApiDto ownListApiDto);

    OwnListApiDto toOwnListApiDto(OwnList ownList);
}
