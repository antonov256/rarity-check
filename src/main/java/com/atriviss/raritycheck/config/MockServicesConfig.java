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
    public OwnItemService getOwnItemService() {
        return new MockOwnItemService();
    }

    @Bean
    @ConditionalOnMissingBean
    public WishItemService getWishItemService() {
        return new MockWishItemService();
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
