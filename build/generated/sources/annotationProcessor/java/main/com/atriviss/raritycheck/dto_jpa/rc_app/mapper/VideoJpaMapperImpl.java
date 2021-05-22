package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.rc_app.VideoJpaDto;
import com.atriviss.raritycheck.model.Video;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T21:39:07+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
@Component
public class VideoJpaMapperImpl implements VideoJpaMapper {

    @Override
    public Video toVideo(VideoJpaDto videoJpaDto) {
        if ( videoJpaDto == null ) {
            return null;
        }

        Long id = null;
        Integer itemId = null;
        String bucketName = null;
        String key = null;

        id = videoJpaDto.getId();
        itemId = videoJpaDto.getItemId();
        bucketName = videoJpaDto.getBucketName();
        key = videoJpaDto.getKey();

        Video video = new Video( id, itemId, bucketName, key );

        return video;
    }

    @Override
    public VideoJpaDto toVideoJpaDto(Video video) {
        if ( video == null ) {
            return null;
        }

        VideoJpaDto videoJpaDto = new VideoJpaDto();

        videoJpaDto.setId( video.getId() );
        videoJpaDto.setItemId( video.getItemId() );
        videoJpaDto.setBucketName( video.getBucketName() );
        videoJpaDto.setKey( video.getKey() );

        return videoJpaDto;
    }

    @Override
    public List<Video> toVideoList(List<VideoJpaDto> videoJpaDtos) {
        if ( videoJpaDtos == null ) {
            return null;
        }

        List<Video> list = new ArrayList<Video>( videoJpaDtos.size() );
        for ( VideoJpaDto videoJpaDto : videoJpaDtos ) {
            list.add( toVideo( videoJpaDto ) );
        }

        return list;
    }

    @Override
    public List<VideoJpaDto> toVideoJpaDtoList(List<Video> videos) {
        if ( videos == null ) {
            return null;
        }

        List<VideoJpaDto> list = new ArrayList<VideoJpaDto>( videos.size() );
        for ( Video video : videos ) {
            list.add( toVideoJpaDto( video ) );
        }

        return list;
    }
}
