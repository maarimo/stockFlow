package com.maarimo.stockFlow.controller;

import com.maarimo.stockFlow.dto.request.MovimentacaoRequestDTO;
import com.maarimo.stockFlow.dto.response.MovimentacaoResponseDTO;
import com.maarimo.stockFlow.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @Operation(summary = "Registrar uma movimentação de estoque")
    @PostMapping
    public ResponseEntity<MovimentacaoResponseDTO> criar(
            @Valid @RequestBody MovimentacaoRequestDTO dto) {

        MovimentacaoResponseDTO movimentacao = movimentacaoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacao);
    }

    @Operation(summary = "Listar movimentações")
    @GetMapping
    public ResponseEntity<List<MovimentacaoResponseDTO>> listar() {

        return ResponseEntity.ok(movimentacaoService.listar());
    }

    @Operation(summary = "Buscar movimentação por ID")
    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(movimentacaoService.buscarPorId(id));
    }
}
