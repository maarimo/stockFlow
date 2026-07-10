package com.maarimo.stockFlow.service;

import com.maarimo.stockFlow.dto.request.ProdutoRequestDTO;
import com.maarimo.stockFlow.dto.response.ProdutoResponseDTO;
import com.maarimo.stockFlow.entity.Categoria;
import com.maarimo.stockFlow.entity.Produto;
import com.maarimo.stockFlow.exception.ResourceNotFoundException;
import com.maarimo.stockFlow.repository.CategoriaRepository;
import com.maarimo.stockFlow.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {

        Categoria categoria = buscarCategoria(dto.categoriaId());

        Produto produto = toEntity(dto, categoria);

        Produto produtoSalvo = produtoRepository.save(produto);

        return toResponse(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listar() {

        return produtoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {

        Produto produto = buscarProduto(id);

        return toResponse(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        Produto produto = buscarProduto(id);

        Categoria categoria = buscarCategoria(dto.categoriaId());

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setCategoria(categoria);

        Produto produtoAtualizado = produtoRepository.save(produto);

        return toResponse(produtoAtualizado);
    }

    public void excluir(Long id) {

        Produto produto = buscarProduto(id);

        produtoRepository.delete(produto);
    }

    private Produto buscarProduto(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado."));
    }

    private Categoria buscarCategoria(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));
    }

    private Produto toEntity(ProdutoRequestDTO dto, Categoria categoria) {

        return Produto.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .quantidade(dto.quantidade())
                .categoria(categoria)
                .build();
    }

    private ProdutoResponseDTO toResponse(Produto produto) {

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getCategoria().getId(),
                produto.getCategoria().getNome()
        );
    }
}