package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.model.Portfolio;

public interface PortfolioService {
    Portfolio findByUserId(Integer userId);
}
