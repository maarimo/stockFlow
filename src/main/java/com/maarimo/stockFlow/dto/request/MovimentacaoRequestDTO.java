package com.maarimo.stockFlow.dto.request;

import com.maarimo.stockFlow.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentacaoRequestDTO(

        @NotNull(message = "O produto é obrigatório.")
        Long produtoId,

        @NotNull(message = "O tipo é obrigatório.")
        TipoMovimentacao tipo,

        @NotNull(message = "A quantidade é obrigatória.")
        @Positive(message = "A quantidade deve ser maior que zero.")
        Integer quantidade

) {}
