package com.maarimo.stockFlow.controller;

import com.maarimo.stockFlow.dto.request.ProdutoRequestDTO;
import com.maarimo.stockFlow.dto.response.ProdutoResponseDTO;
import com.maarimo.stockFlow.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @Operation(summary = "Listar todos os produtos")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {

        return ResponseEntity.ok(produtoService.listar());
    }

    @Operation(summary = "Buscar produto por ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar produto")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(
            @RequestBody @Valid ProdutoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.criar(dto));
    }

    @Operation(summary = "Atualizar produto")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO dto) {

        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @Operation(summary = "Excluir produto")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}