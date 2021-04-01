package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Quality {
    private final Integer value;

    public Quality(Integer value) {
        this.value = value;
    }
}
