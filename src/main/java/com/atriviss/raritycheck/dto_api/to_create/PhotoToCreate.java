package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoToCreate extends PhotoToAddToItem {
    private Integer itemId;

    public PhotoToCreate(Integer itemId, PhotoToAddToItem photoToAddToItem) {
        super(photoToAddToItem.getBucketName(), photoToAddToItem.getKey());
        this.itemId = itemId;
    }
}
