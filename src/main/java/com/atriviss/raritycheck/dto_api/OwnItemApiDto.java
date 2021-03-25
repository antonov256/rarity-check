package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

import java.util.Date;

@Getter
public class OwnItemApiDto {
    private final int id;
    private final ItemApiDto item;
    private final Date addDate;

    public OwnItemApiDto(int id, ItemApiDto item, Date addDate) {
        this.id = id;
        this.item = item;
        this.addDate = addDate;
    }
}
