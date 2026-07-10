package com.maarimo.stockFlow.service;

import com.maarimo.stockFlow.dto.request.CategoriaRequestDTO;
import com.maarimo.stockFlow.dto.response.CategoriaResponseDTO;
import com.maarimo.stockFlow.entity.Categoria;
import com.maarimo.stockFlow.exception.BusinessException;
import com.maarimo.stockFlow.exception.ResourceNotFoundException;
import com.maarimo.stockFlow.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {

        if (categoriaRepository.existsByNome(dto.nome())) {
            throw new BusinessException("Já existe uma categoria com esse nome.");
        }

        Categoria categoria = toEntity(dto);

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return toResponse(categoriaSalva);
    }

    public List<CategoriaResponseDTO> listar() {

        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoriaResponseDTO buscarPorId(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));

        return toResponse(categoria);
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));

        if (!categoria.getNome().equalsIgnoreCase(dto.nome())
                && categoriaRepository.existsByNome(dto.nome())) {

            throw new BusinessException("Já existe uma categoria com esse nome.");
        }

        categoria.setNome(dto.nome());

        Categoria categoriaAtualizada = categoriaRepository.save(categoria);

        return toResponse(categoriaAtualizada);
    }

    public void excluir(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));

        categoriaRepository.delete(categoria);
    }



    private Categoria toEntity(CategoriaRequestDTO dto) {

        return Categoria.builder()
                .nome(dto.nome())
                .build();
    }

    private CategoriaResponseDTO toResponse(Categoria categoria) {

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }



}