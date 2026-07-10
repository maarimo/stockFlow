package com.maarimo.stockFlow.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100)
        String nome,

        @Size(max = 500)
        String descricao,

        @NotNull(message = "O preço é obrigatório.")
        @Positive(message = "O preço deve ser maior que zero.")
        BigDecimal preco,

        @NotNull(message = "A quantidade é obrigatória.")
        @PositiveOrZero(message = "A quantidade não pode ser negativa.")
        Integer quantidade,

        @NotNull(message = "A categoria é obrigatória.")
        Long categoriaId

) {}