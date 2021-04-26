package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.config.security.JwtTokenUtil;
import com.atriviss.raritycheck.dto_api.AuthenticationApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserLoginApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.UserRegisterApiDto;
import com.atriviss.raritycheck.dto_api.rc_user.mapper.UserApiMapper;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/public")
public class AuthRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserApiMapper userApiMapper;


    @PutMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserApiDto registerUser(@RequestBody UserRegisterApiDto userRegisterApiDto) {
        UserApiDto createdUser = userService.register(userRegisterApiDto);
        return createdUser;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationApiDto> login(@RequestBody @Valid UserLoginApiDto loginCredentialsApiDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginCredentialsApiDto.getUsername(), loginCredentialsApiDto.getPassword()
                            )
                    );

            Object principal = authentication.getPrincipal();
            if(!(principal instanceof User))
                throw new BadCredentialsException("Authentication is not User");

            User user = (User) principal;

            String token = jwtTokenUtil.generateAccessToken(user);
            UserApiDto userApiDto = userApiMapper.toDto(user);

            AuthenticationApiDto authenticationApiDto = new AuthenticationApiDto(token, userApiDto);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    )
                    .body(authenticationApiDto);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
