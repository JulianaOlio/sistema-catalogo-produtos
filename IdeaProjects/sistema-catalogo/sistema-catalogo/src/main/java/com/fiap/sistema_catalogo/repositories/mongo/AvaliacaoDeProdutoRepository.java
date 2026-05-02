package com.fiap.sistema_catalogo.repositories.mongo;

import com.fiap.sistema_catalogo.models.documents.AvaliacaoDeProduto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoDeProdutoRepository extends MongoRepository<AvaliacaoDeProduto, String> {
    List<AvaliacaoDeProduto> findByProdutoId(Long produtoId);
}  
