package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.dto_api.mapper.CategoryApiMapper;
import com.atriviss.raritycheck.dto_jpa.pc_app.CategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.CategoryJpaMapper;
import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryApiMapper apiMapper;

    @Autowired
    private CategoryJpaMapper jpaMapper;

    public Optional<CategoryApiDto> findById(Integer id) {
        Optional<CategoryJpaDto> byId = repository.findById(id);
        return byId.map(categoryJpaDto -> apiMapper.toCategoryApiDto(jpaMapper.toCategory(categoryJpaDto)));
    }

    public List<CategoryApiDto> findAll() {
        List<CategoryJpaDto> categoryJpaDtos = repository.findAll();
        List<Category> categories = jpaMapper.toCategoryList(categoryJpaDtos);
        List<CategoryApiDto> categoryDtos = apiMapper.toCategoryApiDtoList(categories);

        return categoryDtos;
    }

    public CategoryApiDto createCategory(CategoryApiDto categoryApiDto) {
        Category category = apiMapper.toCategory(categoryApiDto);
        CategoryJpaDto categoryJpaDto = jpaMapper.toCategoryJpaDto(category);

        CategoryJpaDto savedJpaDto = repository.save(categoryJpaDto);

        Category savedCategory = jpaMapper.toCategory(savedJpaDto);
        CategoryApiDto savedApiDto = apiMapper.toCategoryApiDto(savedCategory);

        return savedApiDto;
    }

    public CategoryApiDto replaceCategory(Integer id, CategoryApiDto newCategoryApiDto) {
        return repository.findById(id)
                .map(c -> {
                    c.setName(newCategoryApiDto.getName());
                    CategoryJpaDto updatedJpaDto = repository.save(c);

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
        repository.deleteById(id);
    }
}
