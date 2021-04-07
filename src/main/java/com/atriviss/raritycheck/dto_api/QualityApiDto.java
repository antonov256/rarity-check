package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QualityApiDto {
    private Integer value;

    public QualityApiDto(Integer value) {
        this.value = value;
    }
}
