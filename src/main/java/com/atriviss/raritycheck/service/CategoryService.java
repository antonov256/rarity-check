package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import com.atriviss.raritycheck.dto_api.mapper.CategoryApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.CategoryToCreate;
import com.atriviss.raritycheck.dto_api.to_create.SubcategoryToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.CategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.mapper.CategoryJpaMapper;
import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.repository.rc_app.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryApiMapper apiMapper;

    @Autowired
    private CategoryJpaMapper jpaMapper;

    @Autowired
    private SubcategoryService subcategoryService;

    @Transactional(transactionManager = "appTransactionManager", readOnly = true)
    public Optional<CategoryApiDto> findById(Integer id) {
        Optional<CategoryJpaDto> optionalJpaDto = repository.findById(id);
        return optionalJpaDto.map(jpaDto -> apiMapper.toCategoryApiDto(jpaMapper.toCategory(jpaDto)));
    }

    @Transactional(transactionManager = "appTransactionManager", readOnly = true)
    public List<CategoryApiDto> findAll(String filter) {
        List<CategoryJpaDto> jpaDtoList;
        if (filter == null) {
            jpaDtoList = repository.findAll();
        } else {
            if ("notEmpty".equals(filter)) {
                jpaDtoList = repository.findAllNotEmpty();
            } else {
                jpaDtoList = repository.findAll();
            }
        }

        List<Category> list = jpaMapper.toCategoryList(jpaDtoList);
        List<CategoryApiDto> apiDtoList = apiMapper.toCategoryApiDtoList(list);

        return apiDtoList;
    }

    @Transactional(transactionManager = "appTransactionManager")
    public CategoryApiDto create(CategoryToCreate toCreate) {
        CategoryJpaDto jpaDto = jpaMapper.toCategoryJpaDto(toCreate);
        CategoryJpaDto savedJpaDto = repository.save(jpaDto);
        CategoryApiDto savedApiDto = apiMapper.toCategoryApiDto(jpaMapper.toCategory(savedJpaDto));

        List<SubcategoryToCreate> subcategories = toCreate.getSubcategories();
        if(subcategories != null && subcategories.size() != 0) {
            List<SubcategoryApiDto> savedSubcategories = subcategories.stream()
                    .peek(s -> s.setCategoryId(savedApiDto.getId()))
                    .map(subcategoryService::create)
                    .collect(Collectors.toList());

            savedApiDto.setSubcategories(savedSubcategories);
        }

        return savedApiDto;
    }

    @Transactional(transactionManager = "appTransactionManager")
    public CategoryApiDto replaceCategory(Integer id, CategoryApiDto newCategoryApiDto) {
        return repository.findById(id)
                .map(jpaDto -> {
                    jpaDto.setName(newCategoryApiDto.getName());
                    CategoryJpaDto updatedJpaDto = repository.save(jpaDto);

                    return apiMapper.toCategoryApiDto(jpaMapper.toCategory(updatedJpaDto));
                })
                .orElseGet(() -> {
                    CategoryJpaDto jpaDto = jpaMapper.toCategoryJpaDto(apiMapper.toCategory(newCategoryApiDto));
                    jpaDto.setId(id);
                    CategoryJpaDto savedJpaDto = repository.save(jpaDto);

                    return apiMapper.toCategoryApiDto(jpaMapper.toCategory(savedJpaDto));
                });
    }

    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("The category can’t be deleted. Provably there are subcategories or items in this category.");
        }
    }
}
