package com.atriviss.raritycheck.dto_api;

import lombok.Data;

@Data
public class ChangePasswordApiDto {
    private String oldPassword;
    private String newPassword;
    private String reNewPassword;
}
