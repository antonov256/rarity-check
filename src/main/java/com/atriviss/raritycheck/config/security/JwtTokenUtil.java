package com.atriviss.raritycheck.config.security;

import com.atriviss.raritycheck.model.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    @Value("${auth.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.jwtIssuer}")
    private String jwtIssuer;

    @Value("${auth.accessTokenExpirationMs}")
    private Long accessTokenExpirationMs;

    @Value("${auth.refreshTokenExpirationMs}")
    private Long refreshTokenExpirationMs;

    public Token generateAccessToken(User user) {
        return generateToken(user, accessTokenExpirationMs);
    }

    public Token generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpirationMs);
    }

    private Token generateToken(User user, Long tokenExpirationMs) {
        Date now = new Date();
        Long duration = now.getTime() + tokenExpirationMs;
        Date expiryDate = new Date(duration);

        String token = Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return new Token(Token.TokenType.REFRESH, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }

    public Integer getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        String userIdStr = claims.getSubject().split(",")[0];

        return Integer.valueOf(userIdStr);
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject().split(",")[1];

        return username;
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        if(token == null) {
            log.error("Token = null");
            return false;
        }

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
