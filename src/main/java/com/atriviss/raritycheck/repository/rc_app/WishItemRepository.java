package com.atriviss.raritycheck.repository.rc_app;

import com.atriviss.raritycheck.dto_jpa.rc_app.WishItemJpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishItemRepository extends JpaRepository<WishItemJpaDto, Integer> {
    List<WishItemJpaDto> findAllByUserId(Integer userId);

    List<WishItemJpaDto> deleteByItemId(Integer itemId);
}
