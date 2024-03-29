package com.example.back_to_flutter.security;

import java.security.Key;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



public class SecurityConstants {
    public static final String SECRET_KEY="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static final int TOKEN_EXPIRATION = 7200000;
    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REGISTER_PATH = "/user/register";
     public static Key getSignKey() { 
                byte[] keyBytes= Decoders.BASE64.decode(SecurityConstants.SECRET_KEY); 
                return Keys.hmacShaKeyFor(keyBytes); 
            } 
}
