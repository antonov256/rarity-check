package com.atriviss.raritycheck.service.mock;

import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.OwnItem;
import com.atriviss.raritycheck.service.ItemService;
import com.atriviss.raritycheck.service.OwnItemService;

import java.util.Date;

public class MockOwnItemService implements OwnItemService {
    private ItemService itemService;

    public MockOwnItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public OwnItem findById(Integer id) {
        Item item = itemService.findById(1);
        OwnItem ownItem = new OwnItem(id, item, new Date());

        return ownItem;
    }
}
