package com.atriviss.raritycheck.dto_api.rc_user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserToCreateApiDto {
    private String email;
    private String username;
    private String password;
    private String rePassword;

    private String name;
    private String surname;
    private String timezone;
}
