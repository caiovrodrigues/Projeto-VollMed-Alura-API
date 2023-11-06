package com.example.api.model;

import com.example.api.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String numero;
    private String uf;
    private String complemento;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.cep = endereco.cep();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if(dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
        if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.numero() != null){
            this.numero = dados.numero();
        }
        if(dados.uf() != null){
            this.uf = dados.uf();
        }
        if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }
    }
}
