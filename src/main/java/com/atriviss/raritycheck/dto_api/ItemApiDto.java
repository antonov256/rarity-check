package com.atriviss.raritycheck.dto_api;

import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.model.Subcategory;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemApiDto {
    private final Integer id;
    private final String title;
    private final String description;

    private final Category category;
    private final Subcategory subcategory;
    private final int qualityValue;

    private final List<String> photos;
    private final List<String> videos;

    public ItemApiDto(Integer id, String title, String description, Category category, Subcategory subcategory, int qualityValue, List<String> photos, List<String> videos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.qualityValue = qualityValue;
        this.photos = photos;
        this.videos = videos;
    }
}
