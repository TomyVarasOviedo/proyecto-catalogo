package com.catalogo.proyecto.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Config.JwtUtil;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
    
    public String autenticarUsuario(String username, String password) {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        @SuppressWarnings("unused")
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generarToken(username);
    }
    
}
