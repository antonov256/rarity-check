package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoToAddToItem {
    private String bucketName;
    private String key;

    public PhotoToAddToItem(String bucketName, String key) {
        this.bucketName = bucketName;
        this.key = key;
    }
}
