package com.maarimo.stockFlow.service;

import com.maarimo.stockFlow.entity.Usuario;
import com.maarimo.stockFlow.repository.UsuarioRepository;
import com.maarimo.stockFlow.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado."));

        return new UserDetailsImpl(usuario);
    }
}
