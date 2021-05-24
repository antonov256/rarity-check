package com.atriviss.raritycheck.repository.rc_app;

import com.atriviss.raritycheck.dto_jpa.rc_app.OwnItemJpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnItemRepository extends JpaRepository<OwnItemJpaDto, Integer> {
    List<OwnItemJpaDto> findAllByUserId(Integer userId);

    List<OwnItemJpaDto> deleteByItemId(Integer itemId);
}
