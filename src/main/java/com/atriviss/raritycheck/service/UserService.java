package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.config.security.CookieUtil;
import com.atriviss.raritycheck.config.security.JwtTokenUtil;
import com.atriviss.raritycheck.config.security.SecurityCipher;
import com.atriviss.raritycheck.config.security.Token;
import com.atriviss.raritycheck.controller_rest.exception.ResourceNotFoundException;
import com.atriviss.raritycheck.controller_rest.exception.UserAlreadyExistsException;
import com.atriviss.raritycheck.dto_api.AuthenticationApiDto;
import com.atriviss.raritycheck.dto_api.ChangeUserProfileApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserLoginApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserRegisterApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.mapper.UserApiMapper;
import com.atriviss.raritycheck.dto_jpa.rc_users.UserJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_users.mapper.UserJpaMapper;
import com.atriviss.raritycheck.model.Authority;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.model.UserDetailsContainer;
import com.atriviss.raritycheck.model.UserToCreate;
import com.atriviss.raritycheck.repository.rc_users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserApiMapper apiMapper;

    @Autowired
    private UserJpaMapper jpaMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${service.user.last-seen-update-threshold-in-seconds}")
    private Integer lastSeenUpdateThresholdInSeconds;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserApiMapper userApiMapper;
    @Autowired
    private CookieUtil cookieUtil;

    @Transactional
    public UserApiDto register(UserRegisterApiDto userRegisterApiDto) throws UserAlreadyExistsException {
        if (repository.findByUsername(userRegisterApiDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username '" + userRegisterApiDto.getUsername() + "' exists!");
        }

        if (repository.findByEmail(userRegisterApiDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("There is an account with that email address: " +  userRegisterApiDto.getEmail());
        }

        if (!userRegisterApiDto.getPassword().equals(userRegisterApiDto.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        UserDetailsContainer userDetails = new UserDetailsContainer(
                userRegisterApiDto.getUsername(),
                passwordEncoder.encode(userRegisterApiDto.getPassword()),
                Collections.singletonList(Authority.USER)
        );

        UserToCreate userToCreate = new UserToCreate(
                userDetails,
                userRegisterApiDto.getName(),
                userRegisterApiDto.getSurname(),
                userRegisterApiDto.getEmail(),
                TimeZone.getTimeZone(userRegisterApiDto.getTimezone()),
                OffsetDateTime.now()
        );

        UserJpaDto registeredUserJpaDto = repository.save(jpaMapper.toJpaDto(userToCreate));

        User registeredUser = jpaMapper.toModel(registeredUserJpaDto);
        UserApiDto registeredUserApiDto = apiMapper.toDto(registeredUser);

        return registeredUserApiDto;
    }

    @Transactional
    public void updateUserLastSeen(User user){
        Duration duration = Duration.between(user.getLastSeen(), OffsetDateTime.now());
        if(duration.getSeconds() > lastSeenUpdateThresholdInSeconds)
            repository.updateLastSeen(user.getId(), OffsetDateTime.now());
    }

    public Optional<UserApiDto> getUserById(Integer id) {
        Optional<UserJpaDto> optionalUserJpaDto = repository.findById(id);
        return optionalUserJpaDto.map(jpaMapper::toModel).map(apiMapper::toDto);
    }

    public Optional<UserApiDto> getUserByUsername(String username) {
        Optional<UserJpaDto> optionalUserJpaDto = repository.findByUsername(username);
        return optionalUserJpaDto.map(jpaMapper::toModel).map(apiMapper::toDto);
    }

    public boolean checkOldPasswordIsValid(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Transactional
    public void updatePassword(User user, String newPassword) {
        repository.updatePassword(user.getId(), passwordEncoder.encode(newPassword));
    }

    @Transactional
    public UserApiDto updateProfile(User user, ChangeUserProfileApiDto changeUserProfileApiDto) {
        UserJpaDto userJpaDtoToUpdate = repository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("user", user.getId()));

        if(changeUserProfileApiDto.getName() != null && !changeUserProfileApiDto.getName().isEmpty())
            userJpaDtoToUpdate.setName(changeUserProfileApiDto.getName());

        if(changeUserProfileApiDto.getSurname() != null && !changeUserProfileApiDto.getSurname().isEmpty())
            userJpaDtoToUpdate.setSurname(changeUserProfileApiDto.getSurname());

        if(changeUserProfileApiDto.getEmail() != null && !changeUserProfileApiDto.getEmail().isEmpty())
            userJpaDtoToUpdate.setEmail(changeUserProfileApiDto.getEmail());

        if(changeUserProfileApiDto.getTimeZone() != null && !changeUserProfileApiDto.getTimeZone().isEmpty())
            userJpaDtoToUpdate.setTimezone(changeUserProfileApiDto.getTimeZone());

        repository.save(userJpaDtoToUpdate);

        return apiMapper.toDto(jpaMapper.toModel(userJpaDtoToUpdate));
    }

    public ResponseEntity<AuthenticationApiDto> login(UserLoginApiDto userLoginApiDto, String accessToken, String refreshToken) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginApiDto.getUsername(), userLoginApiDto.getPassword()
                        )
                );

        Object principal = authentication.getPrincipal();
        if(!(principal instanceof User))
            throw new BadCredentialsException("Authentication is not User");

        User user = (User) principal;

        Boolean accessTokenValid = jwtTokenUtil.validate(accessToken);
        Boolean refreshTokenValid = jwtTokenUtil.validate(refreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        Token newAccessToken;
        Token newRefreshToken;

        if (!accessTokenValid && !refreshTokenValid) {
            newAccessToken = jwtTokenUtil.generateAccessToken(user);
            newRefreshToken = jwtTokenUtil.generateRefreshToken(user);

            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        } else {
            if (accessTokenValid && refreshTokenValid) {
                newAccessToken = jwtTokenUtil.generateAccessToken(user);
                newRefreshToken = jwtTokenUtil.generateRefreshToken(user);

                addAccessTokenCookie(responseHeaders, newAccessToken);
                addRefreshTokenCookie(responseHeaders, newRefreshToken);
            } else {
                if (!accessTokenValid && refreshTokenValid) {
                    newAccessToken = jwtTokenUtil.generateAccessToken(user);
                    newRefreshToken = jwtTokenUtil.generateRefreshToken(user);

                    addAccessTokenCookie(responseHeaders, newAccessToken);
                    addRefreshTokenCookie(responseHeaders, newRefreshToken);
                } else {
                    // only for token in response body
                    newAccessToken = jwtTokenUtil.generateAccessToken(user);
                    newRefreshToken = jwtTokenUtil.generateRefreshToken(user);

                    addAccessTokenCookie(responseHeaders, newAccessToken);
                    addRefreshTokenCookie(responseHeaders, newRefreshToken);
                }
            }
        }

        responseHeaders.add(HttpHeaders.AUTHORIZATION, SecurityCipher.encrypt(newAccessToken.getTokenValue()));

        AuthenticationApiDto authenticationApiDto = new AuthenticationApiDto(
                AuthenticationApiDto.SuccessFailure.SUCCESS,
                "Auth successful. Tokens are created in cookie.",
                SecurityCipher.encrypt(newAccessToken.getTokenValue()),
                userApiMapper.toDto(user)
        );
        return ResponseEntity.ok().headers(responseHeaders).body(authenticationApiDto);
    }

    public ResponseEntity<AuthenticationApiDto> refresh(String accessToken, String refreshToken) {
        Boolean refreshTokenValid = jwtTokenUtil.validate(refreshToken);
        if (!refreshTokenValid) {
            throw new IllegalArgumentException("Refresh Token is invalid!");
        }

        String username = jwtTokenUtil.getUsername(accessToken);
        Integer userId = jwtTokenUtil.getUserId(accessToken);

        Optional<UserJpaDto> userJpaDtoOptional = repository.findById(userId);
        if(userJpaDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException(User.class, userId);
        }

        User user = jpaMapper.toModel(userJpaDtoOptional.get());

        Token newAccessToken = jwtTokenUtil.generateAccessToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        addAccessTokenCookie(responseHeaders, newAccessToken);

        AuthenticationApiDto loginResponse = new AuthenticationApiDto(
                AuthenticationApiDto.SuccessFailure.SUCCESS,
                "Auth successful. Tokens are created in cookie.",
                SecurityCipher.encrypt(newAccessToken.getTokenValue()),
                userApiMapper.toDto(user)
        );

        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    public ResponseEntity<?> logout() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteAccessTokenCookie().toString());
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteRefreshTokenCookie().toString());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .build();
    }
}
