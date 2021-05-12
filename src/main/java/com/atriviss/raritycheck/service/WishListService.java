package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.controller_rest.exception.OperationDeniedOnResourceException;
import com.atriviss.raritycheck.controller_rest.exception.ResourceNotFoundException;
import com.atriviss.raritycheck.dto_api.WishItemApiDto;
import com.atriviss.raritycheck.dto_api.WishListApiDto;
import com.atriviss.raritycheck.dto_api.mapper.WishItemApiMapper;
import com.atriviss.raritycheck.dto_api.mapper.WishListApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.WishItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.WishItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.WishItemJpaMapper;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.WishItem;
import com.atriviss.raritycheck.model.WishList;
import com.atriviss.raritycheck.repository.ItemRepository;
import com.atriviss.raritycheck.repository.WishItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WishItemRepository wishItemRepository;
    @Autowired
    private WishItemJpaMapper wishItemJpaMapper;
    @Autowired
    private WishItemApiMapper wishItemApiMapper;

    @Autowired
    private WishListApiMapper wishListApiMapper;

    public WishListApiDto wishListForUserId(Integer userId) {
        List<WishItemJpaDto> jpaDtoList = wishItemRepository.findAllByUserId(userId);
        WishList wishList = new WishList(wishItemJpaMapper.toWishItemList(jpaDtoList));

        return wishListApiMapper.toWishListApiDto(wishList);
    }

    public WishItemApiDto addWishItem(WishItemToAddToUser wishItemToAddToUser) {
        ItemJpaDto itemJpaDto = itemRepository.findById(wishItemToAddToUser.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException(Item.class, wishItemToAddToUser.getItemId()));

        WishItemJpaDto wishItemJpaDto = wishItemJpaMapper.toWishItemJpaDto(wishItemToAddToUser);
        wishItemJpaDto.setItem(itemJpaDto);

        WishItemJpaDto savedWishItemJpaDto = wishItemRepository.save(wishItemJpaDto);

        return wishItemApiMapper.toWishItemApiDto(wishItemJpaMapper.toWishItem(savedWishItemJpaDto));
    }

    public void deleteWishItemById(Integer userId, Integer wishItemId) {
        Optional<WishItemJpaDto> optionalWishItemJpaDto = wishItemRepository.findById(wishItemId);
        if(optionalWishItemJpaDto.isPresent()) {
            if(optionalWishItemJpaDto.get().getUserId().equals(userId)){
                wishItemRepository.deleteById(wishItemId);
            } else {
                throw new OperationDeniedOnResourceException(WishItem.class, HttpMethod.DELETE, wishItemId);
            }
        }
    }

    public void deleteWishItemsByItemId(Integer itemId) {
        List<WishItemJpaDto> deleted = wishItemRepository.deleteByItemId(itemId);
    }
}
