package com.example.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        String telefone,
        DadosEndereco endereco
        ) {
}
