package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.controller_rest.exception.UserAlreadyExistsException;
import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
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

    public Optional<UserApiDto> getUserById(Integer id) {
        Optional<UserJpaDto> optionalUserJpaDto = repository.findById(id);
        return optionalUserJpaDto.map(jpaMapper::toModel).map(apiMapper::toDto);
    }

    public Optional<UserApiDto> getUserByUsername(String username) {
        Optional<UserJpaDto> optionalUserJpaDto = repository.findByUsername(username);
        return optionalUserJpaDto.map(jpaMapper::toModel).map(apiMapper::toDto);
    }
}
