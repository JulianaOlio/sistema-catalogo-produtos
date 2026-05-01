package com.fiap.sistema_catalogo.services;

import com.fiap.sistema_catalogo.models.entities.Categoria;
import com.fiap.sistema_catalogo.repositories.jpa.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria save(Categoria categoria) {
        var novaCategoria = new Categoria();

        novaCategoria.setCategoria(categoria.getCategoria());
        return categoriaRepository.save(novaCategoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

}
