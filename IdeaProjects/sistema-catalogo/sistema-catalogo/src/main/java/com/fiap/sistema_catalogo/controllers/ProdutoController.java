package com.fiap.sistema_catalogo.controllers;

import com.fiap.sistema_catalogo.dtos.AtualizarProdutoDto;
import com.fiap.sistema_catalogo.dtos.SalvarProdutoDto;
import com.fiap.sistema_catalogo.models.documents.RelatorioDeProduto;
import com.fiap.sistema_catalogo.models.entities.Produto;
import com.fiap.sistema_catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> inserirProduto(@Valid @RequestBody SalvarProdutoDto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarProduto(@Valid @RequestBody AtualizarProdutoDto produto){
        produtoService.atualizarProduto(produto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id){
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProduto(id));
    }

    @GetMapping("/{id}/relatorio")
    public ResponseEntity<RelatorioDeProduto> gerarRelatorio(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.gerarRelatorio(id));
    }
}
