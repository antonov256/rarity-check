package com.atriviss.raritycheck.service.mock;

import com.atriviss.raritycheck.model.*;
import com.atriviss.raritycheck.service.OwnItemService;

import java.util.Collections;
import java.util.Date;

public class MockOwnItemService implements OwnItemService {
    @Override
    public OwnItem findById(Integer id) {
        Classification classification = new Classification(
                new Category(1, "category1", Collections.emptyList())
        );

        Item item = new Item(id,
                "item title" + id,
                "item description" + id,
                classification,
                new Quality(80),
                Collections.emptyList(),
                Collections.emptyList()
        );

        OwnItem ownItem = new OwnItem(1, item, new Date());
        return ownItem;
    }
}
