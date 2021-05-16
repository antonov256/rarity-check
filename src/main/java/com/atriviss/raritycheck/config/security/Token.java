package com.atriviss.raritycheck.config.security;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Token {
    private TokenType tokenType;
    private String tokenValue;
    private Long duration;
    private LocalDateTime expiryDate;

    public Token(TokenType tokenType, String tokenValue, Long duration, LocalDateTime expiryDate) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
        this.duration = duration;
        this.expiryDate = expiryDate;
    }

    public enum TokenType {
        ACCESS, REFRESH
    }
}
