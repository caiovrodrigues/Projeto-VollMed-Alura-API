package com.example.api.dto;

import com.example.api.model.Paciente;

public record DadosListagemPaciente(Long id, String nome, String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf());
    }
}
