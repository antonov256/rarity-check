package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class PhotoApiDto {
    private final Long id;
    private final Integer itemId;
    private final String bucketName;
    private final String key;
    private final String url;

    public PhotoApiDto(Long id, Integer itemId, String bucketName, String key, String url) {
        this.id = id;
        this.itemId = itemId;
        this.bucketName = bucketName;
        this.key = key;
        this.url = url;
    }
}
