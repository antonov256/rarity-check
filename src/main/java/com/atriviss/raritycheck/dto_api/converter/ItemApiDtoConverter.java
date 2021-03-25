package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.model.Video;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemApiDtoConverter implements Converter<Item, ItemApiDto> {
    @Override
    public ItemApiDto convert(Item source) {
        ItemApiDto target = new ItemApiDto(
                source.getId(),
                source.getTitle(),
                source.getDescription(),
                source.getClassification().getCategory(),
                source.getClassification().getSubcategory().orElseGet(() -> null),
                source.getQuality().getValue(),
                source.getPhotos().stream().map(Photo::url).collect(Collectors.toList()),
                source.getVideos().stream().map(Video::url).collect(Collectors.toList())
        );

        return target;
    }
}
