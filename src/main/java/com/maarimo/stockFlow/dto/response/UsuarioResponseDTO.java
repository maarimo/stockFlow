package com.maarimo.stockFlow.dto.response;

import com.maarimo.stockFlow.enums.Role;

public record UsuarioResponseDTO(

        Long id,
        String nome,
        String email,
        Role role

) {
}
