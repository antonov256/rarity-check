package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class QualityApiDto {
    private final Integer value;

    public QualityApiDto(Integer value) {
        this.value = value;
    }
}
