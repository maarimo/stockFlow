package com.maarimo.stockFlow.dto.response;

import com.maarimo.stockFlow.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoResponseDTO(

        Long id,
        Long produtoId,
        String produtoNome,
        TipoMovimentacao tipo,
        Integer quantidade,
        LocalDateTime dataHora

) {}