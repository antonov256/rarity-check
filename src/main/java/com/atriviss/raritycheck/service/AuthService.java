package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.config.security.CookieUtil;
import com.atriviss.raritycheck.config.security.JwtTokenUtil;
import com.atriviss.raritycheck.config.security.Token;
import com.atriviss.raritycheck.controller_rest.exception.ResourceNotFoundException;
import com.atriviss.raritycheck.dto_api.AuthorizationResponse;
import com.atriviss.raritycheck.dto_api.rc_user.UserLoginApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.mapper.UserApiMapper;
import com.atriviss.raritycheck.dto_jpa.rc_users.UserJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_users.mapper.UserJpaMapper;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.repository.rc_users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJpaMapper userJpaMapper;
    @Autowired
    private UserApiMapper userApiMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CookieUtil cookieUtil;

    public ResponseEntity<AuthorizationResponse> login(UserLoginApiDto userLoginApiDto, String accessToken, String refreshToken) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginApiDto.getUsername(), userLoginApiDto.getPassword()
                        )
                );

        Object principal = authentication.getPrincipal();
        if(!(principal instanceof User)) {
            throw new BadCredentialsException("Authentication is not User");
        }

        User user = (User) principal;
        Token newAccessToken = jwtTokenUtil.generateAccessToken(user);
        Token newRefreshToken = jwtTokenUtil.generateRefreshToken(user);

        HttpHeaders responseHeaders = new HttpHeaders();

        HttpCookie accessTokenCookie = cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration());
        responseHeaders.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        HttpCookie refreshTokenCookie = cookieUtil.createRefreshTokenCookie(newRefreshToken.getTokenValue(), newRefreshToken.getDuration());
        responseHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        AuthorizationResponse authorizationResponse = new AuthorizationResponse(
                AuthorizationResponse.Status.SUCCESS,
                "Login successful. Tokens are created in cookies.",
                userApiMapper.toDto(user)
        );

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(authorizationResponse);
    }

    public ResponseEntity<AuthorizationResponse> refresh(String accessToken, String refreshToken) {
        Boolean refreshTokenValid = jwtTokenUtil.validate(refreshToken);
        if (!refreshTokenValid) {
            throw new IllegalArgumentException("Refresh Token is invalid!");
        }

        Integer userId = jwtTokenUtil.getUserId(accessToken);

        Optional<UserJpaDto> userJpaDtoOptional = userRepository.findById(userId);
        if(userJpaDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException(User.class, userId);
        }

        User user = userJpaMapper.toModel(userJpaDtoOptional.get());
        Token newAccessToken = jwtTokenUtil.generateAccessToken(user);

        HttpHeaders responseHeaders = new HttpHeaders();

        HttpCookie accessTokenCookie = cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration());
        responseHeaders.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());

        AuthorizationResponse loginResponse = new AuthorizationResponse(
                AuthorizationResponse.Status.SUCCESS,
                "Refresh access successful. Tokens are created in cookies.",
                userApiMapper.toDto(user)
        );

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(loginResponse);
    }

    public ResponseEntity<?> logout() {
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpCookie deleteAccessTokenCookie = cookieUtil.deleteAccessTokenCookie();
        httpHeaders.add(HttpHeaders.SET_COOKIE, deleteAccessTokenCookie.toString());
        HttpCookie deleteRefreshTokenCookie = cookieUtil.deleteRefreshTokenCookie();
        httpHeaders.add(HttpHeaders.SET_COOKIE, deleteRefreshTokenCookie.toString());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .build();
    }
}
