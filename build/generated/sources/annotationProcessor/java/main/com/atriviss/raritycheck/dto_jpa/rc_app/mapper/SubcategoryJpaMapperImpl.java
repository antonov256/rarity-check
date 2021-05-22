package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.SubcategoryToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.model.Subcategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T21:39:08+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
@Component
public class SubcategoryJpaMapperImpl implements SubcategoryJpaMapper {

    @Override
    public Subcategory toSubcategory(SubcategoryJpaDto subcategoryJpaDto) {
        if ( subcategoryJpaDto == null ) {
            return null;
        }

        Integer id = null;
        Integer categoryId = null;
        String name = null;

        id = subcategoryJpaDto.getId();
        categoryId = subcategoryJpaDto.getCategoryId();
        name = subcategoryJpaDto.getName();

        Subcategory subcategory = new Subcategory( id, categoryId, name );

        return subcategory;
    }

    @Override
    public SubcategoryJpaDto toSubcategoryJpaDto(Subcategory subcategory) {
        if ( subcategory == null ) {
            return null;
        }

        SubcategoryJpaDto subcategoryJpaDto = new SubcategoryJpaDto();

        subcategoryJpaDto.setId( subcategory.getId() );
        subcategoryJpaDto.setCategoryId( subcategory.getCategoryId() );
        subcategoryJpaDto.setName( subcategory.getName() );

        return subcategoryJpaDto;
    }

    @Override
    public SubcategoryJpaDto toSubcategoryJpaDto(SubcategoryToCreate subcategoryToCreate) {
        if ( subcategoryToCreate == null ) {
            return null;
        }

        SubcategoryJpaDto subcategoryJpaDto = new SubcategoryJpaDto();

        subcategoryJpaDto.setCategoryId( subcategoryToCreate.getCategoryId() );
        subcategoryJpaDto.setName( subcategoryToCreate.getName() );

        return subcategoryJpaDto;
    }

    @Override
    public List<Subcategory> toSubcategoryList(List<SubcategoryJpaDto> subcategoryJpaDtoList) {
        if ( subcategoryJpaDtoList == null ) {
            return null;
        }

        List<Subcategory> list = new ArrayList<Subcategory>( subcategoryJpaDtoList.size() );
        for ( SubcategoryJpaDto subcategoryJpaDto : subcategoryJpaDtoList ) {
            list.add( toSubcategory( subcategoryJpaDto ) );
        }

        return list;
    }

    @Override
    public List<SubcategoryJpaDto> toSubcategoryJpaDtoList(List<Subcategory> subcategoryList) {
        if ( subcategoryList == null ) {
            return null;
        }

        List<SubcategoryJpaDto> list = new ArrayList<SubcategoryJpaDto>( subcategoryList.size() );
        for ( Subcategory subcategory : subcategoryList ) {
            list.add( toSubcategoryJpaDto( subcategory ) );
        }

        return list;
    }
}
