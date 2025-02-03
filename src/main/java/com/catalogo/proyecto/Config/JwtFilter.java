package com.catalogo.proyecto.Config;

import java.io.IOException;
import java.util.Collections;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter{

    private final JwtUtil jwtUtil;
    
        public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extraerToken(request);
        if (token != null && jwtUtil.validarToken(token)) {
            String username = jwtUtil.usuarioToken(token);
            var authToken = new UsernamePasswordAuthenticationToken(
                username, null, Collections.emptyList()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
    
    private String extraerToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer ")) 
        ? header.substring(7) 
        : null;
    }
}
