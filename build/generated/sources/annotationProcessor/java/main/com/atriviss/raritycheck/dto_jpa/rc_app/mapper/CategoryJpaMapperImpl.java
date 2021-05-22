package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.CategoryToCreate;
import com.atriviss.raritycheck.dto_api.to_create.SubcategoryToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.CategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.model.Subcategory;
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
public class CategoryJpaMapperImpl implements CategoryJpaMapper {

    @Override
    public Category toCategory(CategoryJpaDto categoryJpaDto) {
        if ( categoryJpaDto == null ) {
            return null;
        }

        List<Subcategory> subcategories = null;
        Integer id = null;
        String name = null;

        subcategories = subcategoryJpaDtoListToSubcategoryList( categoryJpaDto.getSubcategories() );
        id = categoryJpaDto.getId();
        name = categoryJpaDto.getName();

        Category category = new Category( id, name, subcategories );

        return category;
    }

    @Override
    public CategoryJpaDto toCategoryJpaDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryJpaDto categoryJpaDto = new CategoryJpaDto();

        categoryJpaDto.setId( category.getId() );
        categoryJpaDto.setName( category.getName() );
        categoryJpaDto.setSubcategories( subcategoryListToSubcategoryJpaDtoList( category.getSubcategories() ) );

        return categoryJpaDto;
    }

    @Override
    public CategoryJpaDto toCategoryJpaDto(CategoryToCreate categoryToCreate) {
        if ( categoryToCreate == null ) {
            return null;
        }

        CategoryJpaDto categoryJpaDto = new CategoryJpaDto();

        categoryJpaDto.setName( categoryToCreate.getName() );
        categoryJpaDto.setSubcategories( subcategoryToCreateListToSubcategoryJpaDtoList( categoryToCreate.getSubcategories() ) );

        return categoryJpaDto;
    }

    @Override
    public List<Category> toCategoryList(List<CategoryJpaDto> categoryJpaDtos) {
        if ( categoryJpaDtos == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryJpaDtos.size() );
        for ( CategoryJpaDto categoryJpaDto : categoryJpaDtos ) {
            list.add( toCategory( categoryJpaDto ) );
        }

        return list;
    }

    @Override
    public List<CategoryJpaDto> toCategoryJpaDtoList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryJpaDto> list = new ArrayList<CategoryJpaDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( toCategoryJpaDto( category ) );
        }

        return list;
    }

    protected Subcategory subcategoryJpaDtoToSubcategory(SubcategoryJpaDto subcategoryJpaDto) {
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

    protected List<Subcategory> subcategoryJpaDtoListToSubcategoryList(List<SubcategoryJpaDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Subcategory> list1 = new ArrayList<Subcategory>( list.size() );
        for ( SubcategoryJpaDto subcategoryJpaDto : list ) {
            list1.add( subcategoryJpaDtoToSubcategory( subcategoryJpaDto ) );
        }

        return list1;
    }

    protected SubcategoryJpaDto subcategoryToSubcategoryJpaDto(Subcategory subcategory) {
        if ( subcategory == null ) {
            return null;
        }

        SubcategoryJpaDto subcategoryJpaDto = new SubcategoryJpaDto();

        subcategoryJpaDto.setId( subcategory.getId() );
        subcategoryJpaDto.setCategoryId( subcategory.getCategoryId() );
        subcategoryJpaDto.setName( subcategory.getName() );

        return subcategoryJpaDto;
    }

    protected List<SubcategoryJpaDto> subcategoryListToSubcategoryJpaDtoList(List<Subcategory> list) {
        if ( list == null ) {
            return null;
        }

        List<SubcategoryJpaDto> list1 = new ArrayList<SubcategoryJpaDto>( list.size() );
        for ( Subcategory subcategory : list ) {
            list1.add( subcategoryToSubcategoryJpaDto( subcategory ) );
        }

        return list1;
    }

    protected SubcategoryJpaDto subcategoryToCreateToSubcategoryJpaDto(SubcategoryToCreate subcategoryToCreate) {
        if ( subcategoryToCreate == null ) {
            return null;
        }

        SubcategoryJpaDto subcategoryJpaDto = new SubcategoryJpaDto();

        subcategoryJpaDto.setCategoryId( subcategoryToCreate.getCategoryId() );
        subcategoryJpaDto.setName( subcategoryToCreate.getName() );

        return subcategoryJpaDto;
    }

    protected List<SubcategoryJpaDto> subcategoryToCreateListToSubcategoryJpaDtoList(List<SubcategoryToCreate> list) {
        if ( list == null ) {
            return null;
        }

        List<SubcategoryJpaDto> list1 = new ArrayList<SubcategoryJpaDto>( list.size() );
        for ( SubcategoryToCreate subcategoryToCreate : list ) {
            list1.add( subcategoryToCreateToSubcategoryJpaDto( subcategoryToCreate ) );
        }

        return list1;
    }
}
