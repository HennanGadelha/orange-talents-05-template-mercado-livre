package com.mercadolivre.config.security;

import com.mercadolivre.usuario.Usuario;
import com.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Autenticacao implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByEmailLogin(username);
        if(usuario.isPresent()){
            return  usuario.get();
        }

        throw new UsernameNotFoundException("Dados invalidos");
    }


}
