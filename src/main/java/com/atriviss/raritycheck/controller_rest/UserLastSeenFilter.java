package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Component
public class UserLastSeenFilter extends OncePerRequestFilter {
    @Autowired
    private UserFromPrincipalExtractor userExtractor;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            User user = userExtractor.extract(principal);
            userService.updateUserLastSeen(user);
        }

        chain.doFilter(request, response);
    }
}
