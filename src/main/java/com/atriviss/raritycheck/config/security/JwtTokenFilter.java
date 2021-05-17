package com.atriviss.raritycheck.config.security;

import com.atriviss.raritycheck.dto_jpa.rc_users.mapper.UserJpaMapper;
import com.atriviss.raritycheck.repository.rc_users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJpaMapper userJpaMapper;

    @Value("${auth.accessTokenCookieName}")
    private String accessTokenCookieName;

    private static final String BEARER_ = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        String token = getJwtFromCookie(request);

        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        UserDetails userDetails = userRepository
                .findByUsername(jwtTokenUtil.getUsername(token))
                .map(userJpaMapper::toModel)
                .orElse(null);

        Collection<? extends GrantedAuthority> authorities;
        if (userDetails != null) {
            authorities = userDetails.getAuthorities();
        } else {
            authorities = new ArrayList<>();
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                authorities
        );

        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        chain.doFilter(request, response);
    }

    private String getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (accessTokenCookieName.equals(cookie.getName())) {
                String accessToken = cookie.getValue();
                if (accessToken == null) {
                    return null;
                }

                accessToken = accessToken.trim();
                if(accessToken.equals("null")) {
                    return null;
                }

                return SecurityCipher.decrypt(accessToken.trim());
            }
        }

        return null;
    }

    private String getJwtFromAuthorizationHeader(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader)) {
            String accessToken;
            if (authHeader.startsWith(BEARER_)) {
                accessToken = authHeader.substring(BEARER_.length()).trim();
            } else {
                accessToken = authHeader.trim();
            }

            if(accessToken.equals("null")) {
                return null;
            }

            return SecurityCipher.decrypt(accessToken);
        }

        return null;
    }
}
