package com.ti.interdisciplinar242.Configurations;

import com.ti.interdisciplinar242.Interfaces.TesteInterface;
import com.ti.interdisciplinar242.Models.TesteModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter /*extends OncePerRequestFilter*/ {
/*
    @Autowired
    TokenService tokenService;
    @Autowired
    TesteInterface testeInterface;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validacaoToken(token);

        if(login != null){
            TesteModel testeModel = testeInterface.findByLogin(login).orElseThrow(() -> new RuntimeException("Não encontrado."));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(testeModel, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Autorização");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }*/
}
