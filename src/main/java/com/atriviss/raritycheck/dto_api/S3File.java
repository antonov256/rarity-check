package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class S3File {
    private String bucketName;
    private String key;

    public S3File(String bucketName, String key) {
        this.bucketName = bucketName;
        this.key = key;
    }

    public String getUrl() {
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
