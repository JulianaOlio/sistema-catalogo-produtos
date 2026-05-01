package com.fiap.sistema_catalogo.repositories.jpa;

import com.fiap.sistema_catalogo.models.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
