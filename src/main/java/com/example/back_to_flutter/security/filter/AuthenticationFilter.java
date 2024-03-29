package com.example.back_to_flutter.security.filter;

import java.io.IOException;
import java.util.Date;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.back_to_flutter.model.LoginCredentials;
import com.example.back_to_flutter.security.SecurityConstants;
import com.example.back_to_flutter.security.manager.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    CustomAuthenticationManager manager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
       
          try 
          {
                LoginCredentials user= new ObjectMapper().readValue(request.getInputStream(), LoginCredentials.class);
                System.out.println(user.getUsername() + '\n' + user.getPassword());
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return manager.authenticate(authentication);
            } 
        catch (IOException e) {
            
            throw new RuntimeException();
        } 
      
        
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               response.getWriter().write(failed.getMessage());
               response.getWriter().flush();
             
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
               //  KeyPair keyPair = generateKeyPair();
            // String token = Jwts.builder()
            //    .setSubject(authResult.getName())
            //    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
            //    .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256).compact();
            //     response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + " " +token);
           String token= Jwts.builder() 
                .setSubject(authResult.getName()) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION)) 
                .signWith(SecurityConstants.getSignKey(), SignatureAlgorithm.HS256).compact(); 
                response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + " " +token);
    }
           
    // static public KeyPair generateKeyPair() {
    // try {
    //     KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    //     keyPairGenerator.initialize(2048); // Adjust the key size as needed
    //     return keyPairGenerator.generateKeyPair();
    // } catch (NoSuchAlgorithmException e) {
    //     // Handle the exception appropriately
    //     e.printStackTrace();
    //     return null;
    // }
//}
    
}
