package com.atriviss.raritycheck.config;

import com.atriviss.raritycheck.service.*;
import com.atriviss.raritycheck.service.mock.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServicesConfig {
    @Bean
    @ConditionalOnMissingBean
    public ItemService getItemService() {
        return new MockItemService();
    }

    @Bean
    @ConditionalOnMissingBean
    public OwnItemService getOwnItemService(ItemService itemService) {
        return new MockOwnItemService(itemService);
    }

    @Bean
    @ConditionalOnMissingBean
    public WishItemService getWishItemService(ItemService itemService) {
        return new MockWishItemService(itemService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PortfolioService getPortfolioService(OwnItemService ownItemService, WishItemService wishItemService) {
        return new MockPortfolioService(ownItemService, wishItemService);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserService getUserService(PortfolioService portfolioService) {
        return new MockUserService(portfolioService);
    }
}
