package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.rc_app.CategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.ClassificationJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.model.Classification;
import com.atriviss.raritycheck.model.Subcategory;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T21:39:08+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
@Component
public class ClassificationJpaMapperImpl implements ClassificationJpaMapper {

    @Autowired
    private CategoryJpaMapper categoryJpaMapper;
    @Autowired
    private SubcategoryJpaMapper subcategoryJpaMapper;

    @Override
    public Classification toClassification(CategoryJpaDto categoryJpaDto, SubcategoryJpaDto subcategoryJpaDto) {
        if ( categoryJpaDto == null && subcategoryJpaDto == null ) {
            return null;
        }

        Category category = null;
        if ( categoryJpaDto != null ) {
            category = categoryJpaMapper.toCategory( categoryJpaDto );
        }
        Subcategory subcategory = null;
        if ( subcategoryJpaDto != null ) {
            subcategory = subcategoryJpaMapper.toSubcategory( subcategoryJpaDto );
        }

        Classification classification = new Classification( category, subcategory );

        return classification;
    }

    @Override
    public CategoryJpaDto toCategoryJpaDto(Classification classification) {
        if ( classification == null ) {
            return null;
        }

        CategoryJpaDto categoryJpaDto = new CategoryJpaDto();

        categoryJpaDto.setId( classificationCategoryId( classification ) );
        categoryJpaDto.setName( classificationCategoryName( classification ) );
        List<Subcategory> subcategories = classificationCategorySubcategories( classification );
        categoryJpaDto.setSubcategories( subcategoryJpaMapper.toSubcategoryJpaDtoList( subcategories ) );

        return categoryJpaDto;
    }

    @Override
    public SubcategoryJpaDto toSubcategoryJpaDto(Classification classification) {
        if ( classification == null ) {
            return null;
        }

        SubcategoryJpaDto subcategoryJpaDto = new SubcategoryJpaDto();

        subcategoryJpaDto.setId( classificationSubcategoryId( classification ) );
        subcategoryJpaDto.setCategoryId( classificationSubcategoryCategoryId( classification ) );
        subcategoryJpaDto.setName( classificationSubcategoryName( classification ) );

        return subcategoryJpaDto;
    }

    @Override
    public ClassificationJpaDto toClassificationJpaDto(Classification classification) {
        if ( classification == null ) {
            return null;
        }

        ClassificationJpaDto classificationJpaDto = new ClassificationJpaDto();

        classificationJpaDto.setCategory( categoryJpaMapper.toCategoryJpaDto( classification.getCategory() ) );
        classificationJpaDto.setSubcategory( subcategoryJpaMapper.toSubcategoryJpaDto( classification.getSubcategory() ) );

        return classificationJpaDto;
    }

    @Override
    public Classification toClassification(ClassificationJpaDto classificationJpaDto) {
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

    private Integer classificationCategoryId(Classification classification) {
        if ( classification == null ) {
            return null;
        }
        Category category = classification.getCategory();
        if ( category == null ) {
            return null;
        }
        Integer id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String classificationCategoryName(Classification classification) {
        if ( classification == null ) {
            return null;
        }
        Category category = classification.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private List<Subcategory> classificationCategorySubcategories(Classification classification) {
        if ( classification == null ) {
            return null;
        }
        Category category = classification.getCategory();
        if ( category == null ) {
            return null;
        }
        List<Subcategory> subcategories = category.getSubcategories();
        if ( subcategories == null ) {
            return null;
        }
        return subcategories;
    }

    private Integer classificationSubcategoryId(Classification classification) {
        if ( classification == null ) {
            return null;
        }
        Subcategory subcategory = classification.getSubcategory();
        if ( subcategory == null ) {
            return null;
        }
        Integer id = subcategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer classificationSubcategoryCategoryId(Classification classification) {
        if ( classification == null ) {
            return null;
        }
        Subcategory subcategory = classification.getSubcategory();
        if ( subcategory == null ) {
            return null;
        }
        Integer categoryId = subcategory.getCategoryId();
        if ( categoryId == null ) {
            return null;
        }
        return categoryId;
    }

    private String classificationSubcategoryName(Classification classification) {
        if ( classification == null ) {
            return null;
        }
        Subcategory subcategory = classification.getSubcategory();
        if ( subcategory == null ) {
            return null;
        }
        String name = subcategory.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
