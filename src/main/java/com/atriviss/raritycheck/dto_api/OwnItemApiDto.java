package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class OwnItemApiDto {
    private final Integer id;
    private final Integer userId;
    private final ItemApiDto item;

    public OwnItemApiDto(Integer id, Integer userId, ItemApiDto item) {
        this.id = id;
        this.userId = userId;
        this.item = item;
    }
}
