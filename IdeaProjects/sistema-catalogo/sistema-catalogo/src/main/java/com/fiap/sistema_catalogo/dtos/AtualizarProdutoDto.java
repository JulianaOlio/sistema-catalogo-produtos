package com.fiap.sistema_catalogo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record AtualizarProdutoDto(
        @NotNull Long id,
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull BigDecimal preco,
        @NotNull Integer estoque,
        @NotNull Set<Long> categoriaIds
) {
}
