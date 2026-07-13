package com.maarimo.stockFlow.controller;

import com.maarimo.stockFlow.dto.request.LoginRequestDTO;
import com.maarimo.stockFlow.dto.request.RegisterRequestDTO;
import com.maarimo.stockFlow.dto.response.LoginResponseDTO;
import com.maarimo.stockFlow.dto.response.UsuarioResponseDTO;
import com.maarimo.stockFlow.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Cadastrar um novo usuário")
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrar(
            @Valid @RequestBody RegisterRequestDTO dto) {

        UsuarioResponseDTO usuario = authService.registrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Operation(summary = "Realizar login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.login(dto));
    }
}
