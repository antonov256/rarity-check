package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemApiDto extends ItemLiteApiDto {
    private List<PhotoApiDto> photos;
    private List<VideoApiDto> videos;

    public ItemApiDto(Integer id, String title, String description, ClassificationApiDto classification, QualityApiDto quality, List<PhotoApiDto> photos, List<VideoApiDto> videos) {
        super(id, title, description, classification, quality);

        this.photos = photos;
        this.videos = videos;
    }
}
