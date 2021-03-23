package com.atriviss.raritycheck.model;

import lombok.Getter;

@Getter
public class Quality {
    private final int value;

    public Quality(int value) {
        this.value = value;
    }
}
