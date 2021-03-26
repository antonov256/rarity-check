package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.model.User;

import java.util.List;

public interface UserService {
    List<User> all();

    User findById(Integer id);
}
