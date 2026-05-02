package com.fiap.sistema_catalogo.services;

import com.fiap.sistema_catalogo.dtos.AtualizarProdutoDto;
import com.fiap.sistema_catalogo.dtos.SalvarProdutoDto;
import com.fiap.sistema_catalogo.models.documents.RelatorioDeProduto;
import com.fiap.sistema_catalogo.models.entities.Categoria;
import com.fiap.sistema_catalogo.models.entities.Produto;
import com.fiap.sistema_catalogo.repositories.jpa.CategoriaRepository;
import com.fiap.sistema_catalogo.repositories.jpa.ProdutoRepository;
import com.fiap.sistema_catalogo.repositories.mongo.AvaliacaoDeProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final AvaliacaoDeProdutoRepository avaliacaoDeProdutoRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            AvaliacaoDeProdutoRepository avaliacaoDeProdutoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.avaliacaoDeProdutoRepository = avaliacaoDeProdutoRepository;
    }

    public Produto salvarProduto(SalvarProdutoDto produtoDto) {
        if (produtoDto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        var produto = new Produto();
        var categorias = buscarCategorias(produtoDto.categoriaIds());

        produto.setNome(produtoDto.nome());
        produto.setDescricao(produtoDto.descricao());
        produto.setPreco(produtoDto.preco());
        produto.setEstoque(produtoDto.estoque());
        produto.setCategorias(categorias);

        return produtoRepository.save(produto);
    }

    public void atualizarProduto(AtualizarProdutoDto produtoDto) {
       var produto = produtoRepository.findById(produtoDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setNome(produtoDto.nome());
        produto.setDescricao(produtoDto.descricao());
        produto.setPreco(produtoDto.preco());
        produto.setEstoque(produtoDto.estoque());
        produto.setCategorias(buscarCategorias(produtoDto.categoriaIds()));

        produtoRepository.save(produto);
    }

    public void excluirProduto(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produtoRepository.deleteById(id);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProduto(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public RelatorioDeProduto gerarRelatorio(Long id) {
        var produto = buscarProduto(id);
        var avaliacoes = avaliacaoDeProdutoRepository.findByProdutoId(id);

        var mediaAvaliacoes = avaliacoes.stream()
                .mapToInt(avaliacao -> avaliacao.getNota())
                .average()
                .orElse(0.0);

        var relatorio = new RelatorioDeProduto();
        relatorio.setProdutoId(produto.getId());
        relatorio.setNome(produto.getNome());
        relatorio.setDescricao(produto.getDescricao());
        relatorio.setMediaAvaliacoes(mediaAvaliacoes);
        relatorio.setQuantidadeDeAvaliacoes(avaliacoes.size());

        return relatorio;
    }

    private HashSet<Categoria> buscarCategorias(java.util.Set<Long> categoriaIds) {
        if (categoriaIds == null || categoriaIds.isEmpty()) {
            throw new IllegalArgumentException("Informe pelo menos uma categoria");
        }

        var categorias = categoriaRepository.findAllById(categoriaIds);
        if (categorias.size() != categoriaIds.size()) {
            throw new EntityNotFoundException("Uma ou mais categorias não foram encontradas");
        }

        return new HashSet<>(categorias);
    }
}
