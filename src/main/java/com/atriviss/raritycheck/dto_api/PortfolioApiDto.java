package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioApiDto {
    private OwnListApiDto ownList;
    private WishListApiDto wishList;

    public PortfolioApiDto(OwnListApiDto ownList, WishListApiDto wishList) {
        this.ownList = ownList;
        this.wishList = wishList;
    }
}
