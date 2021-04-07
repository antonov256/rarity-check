package com.atriviss.raritycheck.dto_api.rc_user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginCredentialsApiDto {
    private String username;
    private String password;

    public UserLoginCredentialsApiDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
