package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@Relation(collectionRelation = "photos", itemRelation = "photo")
public class PhotoApiDto {
    private Long id;
    private Integer itemId;
    private String bucketName;
    private String key;
    private String url;

    public PhotoApiDto(Long id, Integer itemId, String bucketName, String key, String url) {
        this.id = id;
        this.itemId = itemId;
        this.bucketName = bucketName;
        this.key = key;
        this.url = url;
    }
}
