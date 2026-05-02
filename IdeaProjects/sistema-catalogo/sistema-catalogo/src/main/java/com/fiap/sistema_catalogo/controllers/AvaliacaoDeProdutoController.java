package com.fiap.sistema_catalogo.controllers;

import com.fiap.sistema_catalogo.dtos.SalvarAvaliacaoDeProdutoDto;
import com.fiap.sistema_catalogo.models.documents.AvaliacaoDeProduto;
import com.fiap.sistema_catalogo.services.AvaliacaoDeProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoDeProdutoController {
    private final AvaliacaoDeProdutoService avaliacaoDeProdutoService;

    public AvaliacaoDeProdutoController(AvaliacaoDeProdutoService avaliacaoDeProdutoService) {
        this.avaliacaoDeProdutoService = avaliacaoDeProdutoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDeProduto> salvar(@Valid @RequestBody SalvarAvaliacaoDeProdutoDto avaliacaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoDeProdutoService.salvar(avaliacaoDto));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<AvaliacaoDeProduto>> listarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(avaliacaoDeProdutoService.listarPorProduto(produtoId));
    }
}
