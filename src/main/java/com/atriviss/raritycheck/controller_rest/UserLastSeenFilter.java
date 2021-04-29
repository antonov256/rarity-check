package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@Component
public class UserLastSeenFilter implements Filter {
    @Autowired
    private UserFromPrincipalExtractor userExtractor;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Principal principal = req.getUserPrincipal();

        if (principal != null) {
            User user = userExtractor.extract(principal);
            userService.updateUserLastSeen(user);
        }

        chain.doFilter(request, response);
    }
}
