package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
