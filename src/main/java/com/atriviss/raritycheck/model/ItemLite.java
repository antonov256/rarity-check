package com.atriviss.raritycheck.model;

import com.atriviss.raritycheck.Default;
import lombok.Getter;

@Getter
public class ItemLite {
    private final Integer id;
    private final String title;
    private final String description;

    private final Classification classification;
    private final Quality quality;

    @Default
    public ItemLite(Integer id, String title, String description, Classification classification, Quality quality) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.classification = classification;
        this.quality = quality;
    }

    public ItemLite(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.description = item.getDescription();
        this.classification = item.getClassification();
        this.quality = item.getQuality();
    }
}
