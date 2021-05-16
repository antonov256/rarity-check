package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.config.security.SecurityCipher;
import com.atriviss.raritycheck.dto_api.AuthorizationResponse;
import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserLoginApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserRegisterApiDto;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/public")
public class AuthRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserApiDto registerUser(@RequestBody UserRegisterApiDto userRegisterApiDto) {
        UserApiDto createdUser = userService.register(userRegisterApiDto);
        return createdUser;
    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorizationResponse> refreshToken(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {

        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);

        return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponse> login(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            @RequestBody @Valid UserLoginApiDto userLoginApiDto) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginApiDto.getUsername(), userLoginApiDto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);

        ResponseEntity<AuthorizationResponse> loginResponse = userService.login(userLoginApiDto, decryptedAccessToken, decryptedRefreshToken);
        return loginResponse;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseEntity<?> logout = userService.logout();
        SecurityContextHolder.getContext().setAuthentication(null);

        return logout;
    }
}
