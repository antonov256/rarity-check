package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.controller_rest.exception.OperationDeniedOnResourceException;
import com.atriviss.raritycheck.controller_rest.exception.ResourceNotFoundException;
import com.atriviss.raritycheck.dto_api.OwnItemApiDto;
import com.atriviss.raritycheck.dto_api.OwnListApiDto;
import com.atriviss.raritycheck.dto_api.mapper.OwnItemApiMapper;
import com.atriviss.raritycheck.dto_api.mapper.OwnListApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.OwnItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.OwnItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.OwnItemJpaMapper;
import com.atriviss.raritycheck.model.OwnItem;
import com.atriviss.raritycheck.model.OwnList;
import com.atriviss.raritycheck.repository.ItemRepository;
import com.atriviss.raritycheck.repository.OwnItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

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

    public OwnListApiDto ownListForUserId(Integer userId) {
        List<OwnItemJpaDto> jpaDtoList = ownItemRepository.findAllByUserId(userId);
        OwnList wishList = new OwnList(ownItemJpaMapper.toOwnItemList(jpaDtoList));

        return wishListApiMapper.toOwnListApiDto(wishList);
    }

    public OwnItemApiDto addOwnItem(OwnItemToAddToUser ownItemToAdd) {
        ItemJpaDto itemJpaDto = itemRepository.findById(ownItemToAdd.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("item", ownItemToAdd.getItemId()));

        OwnItemJpaDto ownItemJpaDto = ownItemJpaMapper.toOwnItemJpaDto(ownItemToAdd);
        ownItemJpaDto.setItem(itemJpaDto);

        OwnItemJpaDto savedOwnItemJpaDto = ownItemRepository.save(ownItemJpaDto);

        return ownItemApiMapper.toOwnItemApiDto(ownItemJpaMapper.toOwnItem(savedOwnItemJpaDto));
    }

    public void deleteOwnItemById(Integer userId, Integer itemId) {
        Optional<OwnItemJpaDto> optionalOwnItemJpaDto = ownItemRepository.findById(itemId);
        if(optionalOwnItemJpaDto.isPresent()) {
            if(optionalOwnItemJpaDto.get().getUserId().equals(userId)){
                ownItemRepository.deleteById(itemId);
            } else {
                throw new OperationDeniedOnResourceException(OwnItem.class, HttpMethod.DELETE, itemId);
            }
        }
    }
}
