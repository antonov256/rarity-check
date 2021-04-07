package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Item extends ItemLite {
    private final List<Photo> photos;
    private final List<Video> videos;

    public Item(Integer id, String title, String description, Classification classification, Quality quality, List<Photo> photos, List<Video> videos) {
        super(id, title, description, classification, quality);

        this.photos = photos;
        this.videos = videos;
    }
}
