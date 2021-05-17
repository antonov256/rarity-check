package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.InvalidOldPasswordException;
import com.atriviss.raritycheck.dto_api.ChangePasswordApiDto;
import com.atriviss.raritycheck.dto_api.ChangeUserProfileApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ProfileRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserFromPrincipalExtractor userExtractor;

    @PostMapping("profile/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordApiDto changePasswordApiDto, Principal principal) throws InvalidOldPasswordException {
        User user = userExtractor.extract(principal);

        if(!userService.checkOldPasswordIsValid(user, changePasswordApiDto.getOldPassword())) {
            throw new InvalidOldPasswordException("Invalid old password");
        }

        if(!changePasswordApiDto.getNewPassword().equals(changePasswordApiDto.getReNewPassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        userService.updatePassword(user, changePasswordApiDto.getNewPassword());

        return ResponseEntity.ok().build();
    }


    @PutMapping("profile")
    public ResponseEntity<UserApiDto> changeUserProfile(@RequestBody ChangeUserProfileApiDto changeUserProfileApiDto, Principal principal) throws InvalidOldPasswordException {
        User user = userExtractor.extract(principal);
        UserApiDto updatedUserApiDto = userService.updateProfile(user, changeUserProfileApiDto);

        return ResponseEntity.ok().body(updatedUserApiDto);
    }
}
