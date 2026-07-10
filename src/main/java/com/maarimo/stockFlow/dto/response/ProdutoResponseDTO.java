package com.maarimo.stockFlow.dto.response;

import java.math.BigDecimal;

public record ProdutoResponseDTO(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade,
        Long categoriaId,
        String categoriaNome

) {}