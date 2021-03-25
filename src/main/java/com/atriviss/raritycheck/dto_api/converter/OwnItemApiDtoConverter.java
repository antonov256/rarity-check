package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.model.OwnItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OwnItemApiDtoConverter implements Converter<OwnItem, OwnItemApiDto> {
    private ItemApiDtoConverter itemApiDtoConverter;

    @Autowired
    public OwnItemApiDtoConverter(ItemApiDtoConverter itemApiDtoConverter) {
        this.itemApiDtoConverter = itemApiDtoConverter;
    }

    @Override
    public OwnItemApiDto convert(OwnItem source) {
        OwnItemApiDto target = new OwnItemApiDto(
                source.getId(),
                itemApiDtoConverter.convert(source.getItem()),
                source.getAddDate()
        );

        return target;
    }
}
