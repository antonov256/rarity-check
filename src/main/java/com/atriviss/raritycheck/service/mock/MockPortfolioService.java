package com.atriviss.raritycheck.service.mock;

import com.atriviss.raritycheck.model.OwnList;
import com.atriviss.raritycheck.model.Portfolio;
import com.atriviss.raritycheck.model.WishList;
import com.atriviss.raritycheck.service.OwnItemService;
import com.atriviss.raritycheck.service.PortfolioService;
import com.atriviss.raritycheck.service.WishItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class MockPortfolioService implements PortfolioService {
    private OwnItemService ownItemService;
    private WishItemService wishItemService;

    @Autowired
    public MockPortfolioService(OwnItemService ownItemService, WishItemService wishItemService) {
        this.ownItemService = ownItemService;
        this.wishItemService = wishItemService;
    }

    @Override
    public Portfolio findByUserId(Integer userId) {
        OwnList ownList = new OwnList(Arrays.asList(ownItemService.findById(1), ownItemService.findById(2)));
        WishList wishList = new WishList(Arrays.asList(wishItemService.findById(1), wishItemService.findById(2)));

        return new Portfolio("portfolio for user #" + userId, ownList, wishList);
    }
}
