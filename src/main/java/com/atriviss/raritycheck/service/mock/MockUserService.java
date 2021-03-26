package com.atriviss.raritycheck.service.mock;

import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.PortfolioService;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

public class MockUserService implements UserService {
    private PortfolioService portfolioService;

    @Autowired
    public MockUserService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public List<User> all() {
        return Arrays.asList(findById(1), findById(2), findById(3));
    }

    @Override
    public User findById(Integer id) {
        User user = new User(id, "some_username " + id,
                "some_password",
                "some_name " + id,
                "some_surname " + id,
                "some@gmail.com",
                SimpleTimeZone.getTimeZone(ZoneId.of("GMT+3")),
                portfolioService.findByUserId(id)
        );

        return user;
    }
}
