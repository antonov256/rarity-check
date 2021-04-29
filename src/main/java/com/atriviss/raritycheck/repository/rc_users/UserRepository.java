package com.atriviss.raritycheck.repository.rc_users;

import com.atriviss.raritycheck.dto_jpa.rc_users.UserJpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserJpaDto, Integer> {
    Optional<UserJpaDto> findByEmail(String email);

    Optional<UserJpaDto> findByUsername(String username);

    @Modifying
    @Query("update UserJpaDto user set user.lastSeen = :lastSeen where user.id = :id")
    int updateLastSeen(@Param("id") Integer id, @Param("lastSeen") OffsetDateTime lastSeen);
}
