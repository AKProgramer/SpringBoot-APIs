package com.example.back_to_flutter.security.filter;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.back_to_flutter.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response,
            @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(SecurityConstants.AUTHORIZATION);
        System.out.println(token);
        if (token == null || !token.startsWith(SecurityConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " prefix from the token
        token = token.replace(SecurityConstants.BEARER, "");

        // Verify and parse the JWT token
        Claims claims = Jwts.parserBuilder().setSigningKey(SecurityConstants.getSignKey()).build().parseClaimsJws(token).getBody();
        // Extracting user information from claims
        String username = claims.getSubject(); // Assuming username is stored as subject in JWT claims

        // You might need to extract other information from claims based on your JWT structure

        // Create Authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null);

        // Set the Authentication object in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
