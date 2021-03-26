package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.PortfolioApiDto;
import com.atriviss.raritycheck.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PortfolioApiDtoConverter implements Converter<Portfolio, PortfolioApiDto> {
    private final OwnListApiDtoConverter ownListApiDtoConverter;
    private final WishListApiDtoConverter wishListApiDtoConverter;

    @Autowired
    public PortfolioApiDtoConverter(OwnListApiDtoConverter ownListApiDtoConverter, WishListApiDtoConverter wishListApiDtoConverter) {
        this.ownListApiDtoConverter = ownListApiDtoConverter;
        this.wishListApiDtoConverter = wishListApiDtoConverter;
    }

    @Override
    public PortfolioApiDto convert(Portfolio source) {
        PortfolioApiDto target = new PortfolioApiDto(
                source.getPortfolioDescription(),
                ownListApiDtoConverter.convert(source.getOwnList()),
                wishListApiDtoConverter.convert(source.getWishList())
        );
        return target;
    }
}
