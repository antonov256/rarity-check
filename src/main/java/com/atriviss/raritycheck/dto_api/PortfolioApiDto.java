package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "portfolio", itemRelation = "portfolio")
public class PortfolioApiDto {
    private OwnListApiDto ownList;
    private WishListApiDto wishList;

    public PortfolioApiDto(OwnListApiDto ownList, WishListApiDto wishList) {
        this.ownList = ownList;
        this.wishList = wishList;
    }
}
