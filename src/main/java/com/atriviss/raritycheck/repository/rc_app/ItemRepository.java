package com.atriviss.raritycheck.repository.rc_app;

import com.atriviss.raritycheck.dto_jpa.rc_app.ItemJpaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemJpaDto, Integer>, JpaSpecificationExecutor<ItemJpaDto> {
    Page<ItemJpaDto> findByTitleContains(String titleFragment, Pageable pageable);
}
