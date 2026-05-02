package com.fiap.sistema_catalogo.services;

import com.fiap.sistema_catalogo.dtos.SalvarAvaliacaoDeProdutoDto;
import com.fiap.sistema_catalogo.models.documents.AvaliacaoDeProduto;
import com.fiap.sistema_catalogo.repositories.jpa.ProdutoRepository;
import com.fiap.sistema_catalogo.repositories.mongo.AvaliacaoDeProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoDeProdutoService {
    private final AvaliacaoDeProdutoRepository avaliacaoDeProdutoRepository;
    private final ProdutoRepository produtoRepository;

    public AvaliacaoDeProdutoService(
            AvaliacaoDeProdutoRepository avaliacaoDeProdutoRepository,
            ProdutoRepository produtoRepository
    ) {
        this.avaliacaoDeProdutoRepository = avaliacaoDeProdutoRepository;
        this.produtoRepository = produtoRepository;
    }

    public AvaliacaoDeProduto salvar(SalvarAvaliacaoDeProdutoDto avaliacaoDto) {
        if (!produtoRepository.existsById(avaliacaoDto.produtoId())) {
            throw new EntityNotFoundException("Produto não encontrado");
        }

        var avaliacao = new AvaliacaoDeProduto();
        avaliacao.setProdutoId(avaliacaoDto.produtoId());
        avaliacao.setNomeUsuario(avaliacaoDto.nomeUsuario());
        avaliacao.setComentario(avaliacaoDto.comentario());
        avaliacao.setNota(avaliacaoDto.nota());

        return avaliacaoDeProdutoRepository.save(avaliacao);
    }

    public List<AvaliacaoDeProduto> listarPorProduto(Long produtoId) {
        if (!produtoRepository.existsById(produtoId)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }

        return avaliacaoDeProdutoRepository.findByProdutoId(produtoId);
    }
}
