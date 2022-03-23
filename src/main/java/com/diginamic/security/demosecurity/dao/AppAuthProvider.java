package com.diginamic.security.demosecurity.dao;

import com.diginamic.security.demosecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


public class AppAuthProvider extends DaoAuthenticationProvider {
    @Autowired
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails user = userService.loadUserByUsername(auth.getName());

        if (user == null) {
            throw new BadCredentialsException("Authentication failed");
        }
        if (!user.getPassword().equals(auth.getCredentials().toString())) {
            throw new BadCredentialsException("Authentication failed");
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return super.supports(authentication);
    }
}
