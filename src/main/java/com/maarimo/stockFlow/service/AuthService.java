package com.maarimo.stockFlow.service;

import com.maarimo.stockFlow.dto.request.LoginRequestDTO;
import com.maarimo.stockFlow.dto.request.RegisterRequestDTO;
import com.maarimo.stockFlow.dto.response.LoginResponseDTO;
import com.maarimo.stockFlow.dto.response.UsuarioResponseDTO;
import com.maarimo.stockFlow.entity.Usuario;
import com.maarimo.stockFlow.enums.Role;
import com.maarimo.stockFlow.exception.BusinessException;
import com.maarimo.stockFlow.repository.UsuarioRepository;
import com.maarimo.stockFlow.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsuarioResponseDTO registrar(RegisterRequestDTO dto) {

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Já existe um usuário com esse e-mail.");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .role(Role.FUNCIONARIO)
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getRole()
        );
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.senha()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() ->
                        new BusinessException("Usuário não encontrado."));

        UserDetails userDetails = new com.maarimo.stockFlow.security.UserDetailsImpl(usuario);

        String token = jwtService.gerarToken(userDetails);

        return new LoginResponseDTO(token);
    }

}
