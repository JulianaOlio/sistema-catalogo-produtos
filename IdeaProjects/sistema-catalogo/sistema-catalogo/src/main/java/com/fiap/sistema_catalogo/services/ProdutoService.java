package com.fiap.sistema_catalogo.services;

import com.fiap.sistema_catalogo.dtos.AtualizarProdutoDto;
import com.fiap.sistema_catalogo.dtos.SalvarProdutoDto;
import com.fiap.sistema_catalogo.models.entities.Produto;
import com.fiap.sistema_catalogo.repositories.jpa.CategoriaRepository;
import com.fiap.sistema_catalogo.repositories.jpa.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto salvarProduto(SalvarProdutoDto produtoDto) {
        if (produtoDto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        var produto = new Produto();
        var categoria = categoriaRepository.findById(produtoDto.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        produto.setNome(produtoDto.nome());
        produto.setDescricao(produtoDto.descricao());
        produto.setPreco(produtoDto.preco());
        produto.setEstoque(produtoDto.estoque());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void atualizarProduto(AtualizarProdutoDto produtoDto) {
       var produto = produtoRepository.findById(produtoDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setNome(produtoDto.nome());
        produto.setDescricao(produtoDto.descricao());
        produto.setPreco(produtoDto.preco());
        produto.setEstoque(produtoDto.estoque());

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
}
