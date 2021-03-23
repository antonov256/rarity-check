package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Item {
    private final Integer id;
    private final String title;
    private final String description;

    private final Classification classification;
    private final Quality quality;

    private final List<Photo> photos;
    private final List<Video> videos;

    public Item(Integer id, String title, String description, Classification classification, Quality quality, List<Photo> photos, List<Video> videos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.classification = classification;
        this.quality = quality;
        this.photos = photos;
        this.videos = videos;
    }
}
