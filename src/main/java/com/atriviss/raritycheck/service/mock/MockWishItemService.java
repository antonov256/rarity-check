package com.atriviss.raritycheck.service.mock;

import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.WishItem;
import com.atriviss.raritycheck.service.ItemService;
import com.atriviss.raritycheck.service.WishItemService;

import java.util.Date;

public class MockWishItemService implements WishItemService {
    private ItemService itemService;

    public MockWishItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public WishItem findById(Integer id) {
        Item item = itemService.findById(1);
        WishItem wishItem = new WishItem(id, item, new Date());

        return wishItem;
    }
}
