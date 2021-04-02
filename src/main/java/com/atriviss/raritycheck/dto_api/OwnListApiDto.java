package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OwnListApiDto {
    private List<OwnItemApiDto> items;

    public OwnListApiDto(List<OwnItemApiDto> items) {
        this.items = items;
    }
}
