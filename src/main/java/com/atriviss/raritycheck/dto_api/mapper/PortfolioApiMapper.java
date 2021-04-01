package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.PortfolioApiDto;
import com.atriviss.raritycheck.model.Portfolio;
import org.mapstruct.Mapper;

@Mapper(uses = {OwnListApiMapper.class, WishListApiMapper.class})
public interface PortfolioApiMapper {
    Portfolio toPortfolio(PortfolioApiDto portfolioApiDto);

    PortfolioApiDto toPortfolioApiDto(Portfolio portfolio);
}
