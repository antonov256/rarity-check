package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "ownItems", itemRelation = "ownItem")
public class OwnItemToAddToUser extends OwnItemToAdd {
    private Integer userId;

    public OwnItemToAddToUser(Integer userId, Integer itemId) {
        super(itemId);
        this.userId = userId;
    }
}
