package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.mapper.CategoryApiMapper;
import com.atriviss.raritycheck.dto_jpa.pc_app.CategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.CategoryJpaMapper;
import com.atriviss.raritycheck.model.Category;
import com.atriviss.raritycheck.model.Item;
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
        Optional<CategoryJpaDto> optionalJpaDto = repository.findById(id);
        return optionalJpaDto.map(jpaDto -> apiMapper.toCategoryApiDto(jpaMapper.toCategory(jpaDto)));
    }

    public List<CategoryApiDto> findAll() {
        List<CategoryJpaDto> jpaDtoList = repository.findAll();
        List<Category> list = jpaMapper.toCategoryList(jpaDtoList);
        List<CategoryApiDto> apiDtoList = apiMapper.toCategoryApiDtoList(list);

        return apiDtoList;
    }

    public CategoryApiDto create(CategoryApiDto categoryApiDto) {
        CategoryJpaDto jpaDto = jpaMapper.toCategoryJpaDto(apiMapper.toCategory(categoryApiDto));
        CategoryJpaDto savedJpaDto = repository.save(jpaDto);
        CategoryApiDto savedApiDto = apiMapper.toCategoryApiDto(jpaMapper.toCategory(savedJpaDto));

        return savedApiDto;
    }

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
        repository.deleteById(id);
    }
}
