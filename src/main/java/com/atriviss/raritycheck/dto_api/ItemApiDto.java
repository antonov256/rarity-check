package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemApiDto extends ItemLiteApiDto {
    private final List<PhotoApiDto> photos;
    private final List<VideoApiDto> videos;

    public ItemApiDto(Integer id, String title, String description, ClassificationApiDto classification, QualityApiDto quality, List<PhotoApiDto> photos, List<VideoApiDto> videos) {
        super(id, title, description, classification, quality);

        this.photos = photos;
        this.videos = videos;
    }
}
