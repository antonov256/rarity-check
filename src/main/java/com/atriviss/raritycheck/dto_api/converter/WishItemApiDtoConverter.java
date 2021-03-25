package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.WishItemApiDto;
import com.atriviss.raritycheck.model.WishItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WishItemApiDtoConverter implements Converter<WishItem, WishItemApiDto> {
    private ItemApiDtoConverter itemApiDtoConverter;

    @Autowired
    public WishItemApiDtoConverter(ItemApiDtoConverter itemApiDtoConverter) {
        this.itemApiDtoConverter = itemApiDtoConverter;
    }

    @Override
    public WishItemApiDto convert(WishItem source) {
        WishItemApiDto target = new WishItemApiDto(
                source.getId(),
                itemApiDtoConverter.convert(source.getItem()),
                source.getAddDate()
        );

        return target;
    }
}
