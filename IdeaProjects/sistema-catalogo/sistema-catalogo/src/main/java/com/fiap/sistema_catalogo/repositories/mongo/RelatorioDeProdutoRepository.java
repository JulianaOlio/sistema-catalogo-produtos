package com.fiap.sistema_catalogo.repositories.mongo;

import com.fiap.sistema_catalogo.models.documents.RelatorioDeProduto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioDeProdutoRepository extends MongoRepository<RelatorioDeProduto, String> {

}