package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemLiteApiDto {
    private Integer id;
    private String title;
    private String description;

    private ClassificationApiDto classification;
    private QualityApiDto quality;

    public ItemLiteApiDto(Integer id, String title, String description, ClassificationApiDto classification, QualityApiDto quality) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.classification = classification;
        this.quality = quality;
    }
}
