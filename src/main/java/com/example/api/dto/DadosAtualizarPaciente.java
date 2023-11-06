package com.example.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(@NotNull Long id, String nome, String cpf, DadosEndereco endereco) {
}
