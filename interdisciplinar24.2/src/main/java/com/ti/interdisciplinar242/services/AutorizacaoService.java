package com.ti.interdisciplinar242.services;

import com.ti.interdisciplinar242.Interfaces.UsuarioInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutorizacaoService implements UserDetailsService {


    @Autowired
    UsuarioInterface usuarioInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioInterface.findByLogin(username);
    }
}
