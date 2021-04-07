package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.VideoApiDto;
import com.atriviss.raritycheck.model.Video;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface VideoApiMapper {
    Video toVideo(VideoApiDto videoApiDto);

    VideoApiDto toVideoApiDto(Video video);

    List<Video> toVideoList(List<VideoApiDto> videoApiDtos);

    List<VideoApiDto> toVideoApiDtoList(List<Video> videos);
}
