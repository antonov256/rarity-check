package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "wishItems", itemRelation = "wishItem")
public class WishItemToAddToUser extends WishItemToAdd {
    private Integer userId;

    public WishItemToAddToUser(Integer userId, Integer itemId) {
        super(itemId);
        this.userId = userId;
    }
}
