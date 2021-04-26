package com.atriviss.raritycheck.dto_api.to_create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoToCreate {
    private Integer itemId;
    private String bucketName;
    private String key;
}
