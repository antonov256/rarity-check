package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import com.atriviss.raritycheck.dto_api.mapper.SubcategoryApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.SubcategoryToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.SubcategoryJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.mapper.SubcategoryJpaMapper;
import com.atriviss.raritycheck.model.Subcategory;
import com.atriviss.raritycheck.repository.rc_app.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository repository;

    @Autowired
    private SubcategoryApiMapper apiMapper;

    @Autowired
    private SubcategoryJpaMapper jpaMapper;

    public Optional<SubcategoryApiDto> findById(Integer id) {
        Optional<SubcategoryJpaDto> optionalJpaDto = repository.findById(id);
        return optionalJpaDto.map(jpaDto -> apiMapper.toSubcategoryApiDto(jpaMapper.toSubcategory(jpaDto)));
    }

    public List<SubcategoryApiDto> findAll() {
        List<SubcategoryJpaDto> jpaDtoList = repository.findAll();
        List<Subcategory> list = jpaMapper.toSubcategoryList(jpaDtoList);
        List<SubcategoryApiDto> apiDtoList = apiMapper.toSubcategoryApiDtoList(list);

        return apiDtoList;
    }

    public SubcategoryApiDto create(SubcategoryToCreate toCreate) {
        SubcategoryJpaDto jpaDto = jpaMapper.toSubcategoryJpaDto(toCreate);
        SubcategoryJpaDto savedJpaDto = repository.save(jpaDto);
        SubcategoryApiDto savedApiDto = apiMapper.toSubcategoryApiDto(jpaMapper.toSubcategory(savedJpaDto));

        return savedApiDto;
    }

    @Transactional(transactionManager = "appTransactionManager")
    public SubcategoryApiDto replaceSubcategory(Integer id, SubcategoryApiDto newSubcategoryApiDto) {
        return repository.findById(id)
                .map(jpaDto -> {
                    jpaDto.setName(newSubcategoryApiDto.getName());
                    jpaDto.setCategoryId(newSubcategoryApiDto.getCategoryId());
                    SubcategoryJpaDto updatedJpaDto = repository.save(jpaDto);

                    return apiMapper.toSubcategoryApiDto(jpaMapper.toSubcategory(updatedJpaDto));
                })
                .orElseGet(() -> {
                    SubcategoryJpaDto jpaDto = jpaMapper.toSubcategoryJpaDto(apiMapper.toSubcategory(newSubcategoryApiDto));
                    jpaDto.setId(id);
                    SubcategoryJpaDto savedJpaDto = repository.save(jpaDto);

                    return apiMapper.toSubcategoryApiDto(jpaMapper.toSubcategory(savedJpaDto));
                });
    }

    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("The subcategory can’t be deleted. Provably there are items in this subcategory.");
        }
    }
}
