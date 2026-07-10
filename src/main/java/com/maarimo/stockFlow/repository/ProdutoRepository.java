package com.maarimo.stockFlow.repository;

import com.maarimo.stockFlow.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}