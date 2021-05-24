package com.atriviss.raritycheck.repository.rc_app;

import com.atriviss.raritycheck.dto_jpa.rc_app.CategoryJpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryJpaDto, Integer> {
    @Query("from CategoryJpaDto as category " +
            "where category.id in (select distinct item.classification.category.id from ItemJpaDto as item where item.classification.category.id = category.id) " +
            "order by category.id")
    List<CategoryJpaDto> findAllNotEmpty();
}
