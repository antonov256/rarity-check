package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "ownItems", itemRelation = "ownItem")
public class OwnItemApiDto {
    private Integer id;
    private Integer userId;
    private ItemApiDto item;

    public OwnItemApiDto(Integer id, Integer userId, ItemApiDto item) {
        this.id = id;
        this.userId = userId;
        this.item = item;
    }
}
