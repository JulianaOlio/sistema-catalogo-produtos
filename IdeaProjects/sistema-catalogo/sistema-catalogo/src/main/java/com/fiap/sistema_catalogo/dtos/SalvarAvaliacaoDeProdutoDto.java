package com.fiap.sistema_catalogo.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalvarAvaliacaoDeProdutoDto(
        @NotNull Long produtoId,
        @NotBlank String nomeUsuario,
        @NotBlank String comentario,
        @Min(1) @Max(5) int nota
) {
}
