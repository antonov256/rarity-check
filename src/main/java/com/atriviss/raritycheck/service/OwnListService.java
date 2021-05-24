package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.controller_rest.exception.OperationDeniedOnResourceException;
import com.atriviss.raritycheck.controller_rest.exception.ResourceNotFoundException;
import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.dto_api.OwnListApiDto;
import com.atriviss.raritycheck.dto_api.mapper.OwnItemApiMapper;
import com.atriviss.raritycheck.dto_api.mapper.OwnListApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.OwnItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.rc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.OwnItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.mapper.OwnItemJpaMapper;
import com.atriviss.raritycheck.model.OwnItem;
import com.atriviss.raritycheck.model.OwnList;
import com.atriviss.raritycheck.repository.rc_app.ItemRepository;
import com.atriviss.raritycheck.repository.rc_app.OwnItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnListService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OwnItemRepository ownItemRepository;
    @Autowired
    private OwnItemJpaMapper ownItemJpaMapper;
    @Autowired
    private OwnItemApiMapper ownItemApiMapper;

    @Autowired
    private OwnListApiMapper wishListApiMapper;

    @Transactional(transactionManager = "appTransactionManager", readOnly = true)
    public OwnListApiDto ownListForUserId(Integer userId) {
        List<OwnItemJpaDto> jpaDtoList = ownItemRepository.findAllByUserId(userId);
        OwnList wishList = new OwnList(ownItemJpaMapper.toOwnItemList(jpaDtoList));

        return wishListApiMapper.toOwnListApiDto(wishList);
    }

    @Transactional(transactionManager = "appTransactionManager")
    public OwnItemApiDto addOwnItem(OwnItemToAddToUser ownItemToAdd) {
        ItemJpaDto itemJpaDto = itemRepository.findById(ownItemToAdd.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("item", ownItemToAdd.getItemId()));

        OwnItemJpaDto ownItemJpaDto = ownItemJpaMapper.toOwnItemJpaDto(ownItemToAdd);
        ownItemJpaDto.setItem(itemJpaDto);

        try {
            OwnItemJpaDto savedOwnItemJpaDto = ownItemRepository.save(ownItemJpaDto);
            return ownItemApiMapper.toOwnItemApiDto(ownItemJpaMapper.toOwnItem(savedOwnItemJpaDto));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Item is already in list");
        }
    }

    @Transactional(transactionManager = "appTransactionManager")
    public void deleteOwnItemById(Integer userId, Integer ownItemId) {
        Optional<OwnItemJpaDto> optionalOwnItemJpaDto = ownItemRepository.findById(ownItemId);
        if(optionalOwnItemJpaDto.isPresent()) {
            if(optionalOwnItemJpaDto.get().getUserId().equals(userId)){
                ownItemRepository.deleteById(ownItemId);
            } else {
                throw new OperationDeniedOnResourceException(OwnItem.class, HttpMethod.DELETE, ownItemId);
            }
        }
    }

    public void deleteOwnItemsByItemId(Integer itemId) {
        List<OwnItemJpaDto> deleted = ownItemRepository.deleteByItemId(itemId);
    }
}
