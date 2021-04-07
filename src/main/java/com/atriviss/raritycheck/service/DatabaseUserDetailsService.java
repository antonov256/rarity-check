package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_jpa.rc_users.mapper.UserJpaMapper;
import com.atriviss.raritycheck.repository.rc_users.UserRepository;
import com.atriviss.raritycheck.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserJpaMapper jpaMapper;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(jpaMapper::toModel)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User '%s' not found", username)
                ));
    }
}
