package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WishListApiDto {
    private List<WishItemApiDto> items;

    public WishListApiDto(List<WishItemApiDto> items) {
        this.items = items;
    }
}
