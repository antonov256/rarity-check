package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class ItemLiteApiDto {
    private final Integer id;
    private final String title;
    private final String description;

    private final ClassificationApiDto classification;
    private final QualityApiDto quality;

    public ItemLiteApiDto(Integer id, String title, String description, ClassificationApiDto classification, QualityApiDto quality) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.classification = classification;
        this.quality = quality;
    }
}
