package com.example.back_to_flutter.security.filter;

import java.io.IOException;


import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExaptionHandler extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal( @SuppressWarnings({ "null" }) HttpServletRequest request,  @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {
       try{
         
            filterChain.doFilter(request, response);
       }
       catch(EntityNotFoundException e)
       {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
          response.getWriter().write("Username doesn't exist");
            response.getWriter().flush();
       }
       catch(RuntimeException e)
       {
          System.out.println(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();
       }
    }

    
    
}
