package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.VideoJpaDto;
import com.atriviss.raritycheck.model.Video;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface VideoJpaMapper {
    Video toVideo(VideoJpaDto videoJpaDto);

    VideoJpaDto toVideoJpaDto(Video video);

    List<Video> toVideoList(List<VideoJpaDto> videoJpaDtos);

    List<VideoJpaDto> toVideoJpaDtoList(List<Video> videos);
}
