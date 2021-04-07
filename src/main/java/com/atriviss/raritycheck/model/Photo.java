package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Photo {
    private final Long id;
    private final Integer itemId;
    private final String bucketName;
    private final String key;

    public Photo(Long id, Integer itemId, String bucketName, String key) {
        this.id = id;
        this.itemId = itemId;
        this.bucketName = bucketName;
        this.key = key;
    }

    public String getUrl() {
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
