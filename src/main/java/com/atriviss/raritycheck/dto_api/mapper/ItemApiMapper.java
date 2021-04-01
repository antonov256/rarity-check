package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.VideoApiDto;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.model.Video;
import org.mapstruct.Mapper;

@Mapper(
        uses = {ClassificationApiMapper.class, PhotoApiMapper.class, VideoApiMapper.class},
        imports = {Photo.class, Video.class, PhotoApiDto.class, VideoApiDto.class}
)
public interface ItemApiMapper {
    Item toItem(ItemApiDto itemApiDto);

    ItemApiDto toItemApiDto(Item item);
}
