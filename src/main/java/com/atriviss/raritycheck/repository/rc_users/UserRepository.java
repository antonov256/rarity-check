package com.atriviss.raritycheck.repository.rc_users;

import com.atriviss.raritycheck.dto_jpa.rc_users.UserJpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserJpaDto, Integer> {
    Optional<UserJpaDto> findByEmail(String email);

    Optional<UserJpaDto> findByUsername(String username);
}
