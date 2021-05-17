package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "ownItems", itemRelation = "ownItem")
public class OwnItemToAdd {
    private Integer itemId;

    public OwnItemToAdd(Integer itemId) {
        this.itemId = itemId;
    }
}
