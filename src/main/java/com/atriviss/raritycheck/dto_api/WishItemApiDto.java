package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "wishItems", itemRelation = "wishItem")
public class WishItemApiDto {
    private Integer id;
    private Integer userId;
    private ItemApiDto item;

    public WishItemApiDto(Integer id, Integer userId, ItemApiDto item) {
        this.id = id;
        this.userId = userId;
        this.item = item;
    }
}
