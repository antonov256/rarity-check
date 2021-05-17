package com.atriviss.raritycheck.dto_api;

import lombok.Data;

@Data
public class ChangeUserProfileApiDto {
    private String name;
    private String surname;

    private String email;
    private String timeZone;
}
