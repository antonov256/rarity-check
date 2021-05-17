package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.model.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class UserFromPrincipalExtractor {
    public User extract(Principal principal) {
        if(principal == null)
            throw new BadCredentialsException("principal is null");

        if(!(principal instanceof UsernamePasswordAuthenticationToken))
            throw new BadCredentialsException("Not UsernamePasswordAuthenticationToken");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = ((UsernamePasswordAuthenticationToken) principal);
        Object principalExtracted = usernamePasswordAuthenticationToken.getPrincipal();

        if(!(principalExtracted instanceof User))
            throw new BadCredentialsException("Not user");

        User user = (User) principalExtracted;
        return user;
    }
}
