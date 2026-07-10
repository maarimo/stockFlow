package com.maarimo.stockFlow.service;

import com.maarimo.stockFlow.dto.request.MovimentacaoRequestDTO;
import com.maarimo.stockFlow.dto.response.MovimentacaoResponseDTO;
import com.maarimo.stockFlow.entity.Movimentacao;
import com.maarimo.stockFlow.entity.Produto;
import com.maarimo.stockFlow.enums.TipoMovimentacao;
import com.maarimo.stockFlow.exception.BusinessException;
import com.maarimo.stockFlow.exception.ResourceNotFoundException;
import com.maarimo.stockFlow.repository.MovimentacaoRepository;
import com.maarimo.stockFlow.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoResponseDTO criar(MovimentacaoRequestDTO dto) {

        Produto produto = buscarProduto(dto.produtoId());

        if (dto.tipo() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidade(produto.getQuantidade() + dto.quantidade());
        } else {

            if (produto.getQuantidade() < dto.quantidade()) {
                throw new BusinessException("Estoque insuficiente para realizar a saída.");
            }

            produto.setQuantidade(produto.getQuantidade() - dto.quantidade());
        }

        produtoRepository.save(produto);

        Movimentacao movimentacao = Movimentacao.builder()
                .produto(produto)
                .tipo(dto.tipo())
                .quantidade(dto.quantidade())
                .dataHora(LocalDateTime.now())
                .build();

        Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);

        return toResponse(movimentacaoSalva);
    }

    public List<MovimentacaoResponseDTO> listar() {

        return movimentacaoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MovimentacaoResponseDTO buscarPorId(Long id) {

        Movimentacao movimentacao = buscarMovimentacao(id);

        return toResponse(movimentacao);
    }

    private Produto buscarProduto(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado."));
    }

    private Movimentacao buscarMovimentacao(Long id) {

        return movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Movimentação não encontrada."));
    }

    private MovimentacaoResponseDTO toResponse(Movimentacao movimentacao) {

        return new MovimentacaoResponseDTO(
                movimentacao.getId(),
                movimentacao.getProduto().getId(),
                movimentacao.getProduto().getNome(),
                movimentacao.getTipo(),
                movimentacao.getQuantidade(),
                movimentacao.getDataHora()
        );
    }
}
