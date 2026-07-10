package com.maarimo.stockFlow.repository;

import com.maarimo.stockFlow.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
