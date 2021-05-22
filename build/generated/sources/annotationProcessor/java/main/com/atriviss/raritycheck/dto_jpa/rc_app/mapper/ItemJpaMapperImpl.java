package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.rc_app.ClassificationJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.QualityJpaDto;
import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.model.Classification;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.model.Quality;
import com.atriviss.raritycheck.model.Subcategory;
import com.atriviss.raritycheck.model.Video;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T21:39:07+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
@Component
public class ItemJpaMapperImpl implements ItemJpaMapper {

    @Autowired
    private CategoryJpaMapper categoryJpaMapper;
    @Autowired
    private SubcategoryJpaMapper subcategoryJpaMapper;
    @Autowired
    private PhotoJpaMapper photoJpaMapper;
    @Autowired
    private VideoJpaMapper videoJpaMapper;

    @Override
    public Item toItem(ItemJpaDto itemJpaDto) {
        if ( itemJpaDto == null ) {
            return null;
        }

        List<Photo> photos = null;
        List<Video> videos = null;
        Integer id = null;
        String title = null;
        String description = null;
        Classification classification = null;
        Quality quality = null;

        photos = photoJpaMapper.toPhotoList( itemJpaDto.getPhotos() );
        videos = videoJpaMapper.toVideoList( itemJpaDto.getVideos() );
        id = itemJpaDto.getId();
        title = itemJpaDto.getTitle();
        description = itemJpaDto.getDescription();
        classification = classificationJpaDtoToClassification( itemJpaDto.getClassification() );
        quality = qualityJpaDtoToQuality( itemJpaDto.getQuality() );

        Item item = new Item( id, title, description, classification, quality, photos, videos );

        return item;
    }

    @Override
    public ItemJpaDto toItemJpaDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemJpaDto itemJpaDto = new ItemJpaDto();

        itemJpaDto.setId( item.getId() );
        itemJpaDto.setTitle( item.getTitle() );
        itemJpaDto.setDescription( item.getDescription() );
        itemJpaDto.setClassification( classificationToClassificationJpaDto( item.getClassification() ) );
        itemJpaDto.setQuality( qualityToQualityJpaDto( item.getQuality() ) );
        itemJpaDto.setPhotos( photoJpaMapper.toPhotoJpaDtoList( item.getPhotos() ) );
        itemJpaDto.setVideos( videoJpaMapper.toVideoJpaDtoList( item.getVideos() ) );

        return itemJpaDto;
    }

    @Override
    public List<Item> toItemList(List<ItemJpaDto> itemJpaDtos) {
        if ( itemJpaDtos == null ) {
            return null;
        }

        List<Item> list = new ArrayList<Item>( itemJpaDtos.size() );
        for ( ItemJpaDto itemJpaDto : itemJpaDtos ) {
            list.add( toItem( itemJpaDto ) );
        }

        return list;
    }

    @Override
    public List<ItemJpaDto> toItemJpaDtoList(List<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemJpaDto> list = new ArrayList<ItemJpaDto>( items.size() );
        for ( Item item : items ) {
            list.add( toItemJpaDto( item ) );
        }

        return list;
    }

    protected Classification classificationJpaDtoToClassification(ClassificationJpaDto classificationJpaDto) {
        if ( classificationJpaDto == null ) {
            return null;
        }

        Category category = null;
        Subcategory subcategory = null;

        category = categoryJpaMapper.toCategory( classificationJpaDto.getCategory() );
        subcategory = subcategoryJpaMapper.toSubcategory( classificationJpaDto.getSubcategory() );

        Classification classification = new Classification( category, subcategory );

        return classification;
    }

    protected Quality qualityJpaDtoToQuality(QualityJpaDto qualityJpaDto) {
        if ( qualityJpaDto == null ) {
            return null;
        }

        Integer value = null;

        value = qualityJpaDto.getValue();

        Quality quality = new Quality( value );

        return quality;
    }

    protected ClassificationJpaDto classificationToClassificationJpaDto(Classification classification) {
        if ( classification == null ) {
            return null;
        }

        ClassificationJpaDto classificationJpaDto = new ClassificationJpaDto();

        classificationJpaDto.setCategory( categoryJpaMapper.toCategoryJpaDto( classification.getCategory() ) );
        classificationJpaDto.setSubcategory( subcategoryJpaMapper.toSubcategoryJpaDto( classification.getSubcategory() ) );

        return classificationJpaDto;
    }

    protected QualityJpaDto qualityToQualityJpaDto(Quality quality) {
        if ( quality == null ) {
            return null;
        }

        QualityJpaDto qualityJpaDto = new QualityJpaDto();

        qualityJpaDto.setValue( quality.getValue() );

        return qualityJpaDto;
    }
}
