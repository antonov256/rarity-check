package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.WishListApiDto;
import com.atriviss.raritycheck.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WishListApiDtoConverter implements Converter<WishList, WishListApiDto> {
    private final WishItemApiDtoConverter wishItemApiDtoConverter;

    @Autowired
    public WishListApiDtoConverter(WishItemApiDtoConverter wishItemApiDtoConverter) {
        this.wishItemApiDtoConverter = wishItemApiDtoConverter;
    }

    @Override
    public WishListApiDto convert(WishList source) {
        WishListApiDto target = new WishListApiDto(
                source.getItems().stream()
                        .map(wishItemApiDtoConverter::convert)
                        .collect(Collectors.toList())
        );
        return target;
    }
}
