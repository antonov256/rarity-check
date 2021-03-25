package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

import java.util.List;

@Getter
public class WishListApiDto {
    private final List<WishItemApiDto> items;

    public WishListApiDto(List<WishItemApiDto> items) {
        this.items = items;
    }
}
