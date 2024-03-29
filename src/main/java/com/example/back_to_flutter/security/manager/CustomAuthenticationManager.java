package com.example.back_to_flutter.security.manager;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.back_to_flutter.model.SignupCredentials;
import com.example.back_to_flutter.service.SignupServiceImp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager{
    private SignupServiceImp signupServiceImp;
    private BCryptPasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // System.out.println("principle : " + authentication.getPrincipal().toString());
        // System.out.println("name : " + authentication.getName());
        // issue is here
        SignupCredentials user = signupServiceImp.getUserByUsername(authentication.getPrincipal().toString());
        System.out.println("user name is here" + user.getUsername());
       System.out.println((encoder.matches(authentication.getPrincipal().toString(), user.getPassword())));
        if(!(encoder.matches(authentication.getCredentials().toString(), user.getPassword())))
        {
            throw new BadCredentialsException("You provide an incorrect username or password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
    }
    
}
