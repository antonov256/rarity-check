package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

import java.util.List;

@Getter
public class OwnListApiDto {
    private final List<OwnItemApiDto> items;

    public OwnListApiDto(List<OwnItemApiDto> items) {
        this.items = items;
    }
}
