package com.atriviss.raritycheck.repository.rc_app;

import com.atriviss.raritycheck.dto_jpa.rc_app.VideoJpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoJpaDto, Long> {

}
