package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "wishItems", itemRelation = "wishItem")
public class WishItemToAdd {
    private Integer itemId;

    public WishItemToAdd(Integer itemId) {
        this.itemId = itemId;
    }
}
