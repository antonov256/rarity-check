package com.atriviss.raritycheck.service.mock;

import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.model.Classification;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.Quality;
import com.atriviss.raritycheck.service.ItemService;

import java.util.Collections;

public class MockItemService implements ItemService {
    @Override
    public Item findById(Integer id) {
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

        return item;
    }
}
