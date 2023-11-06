package com.example.api.controller;

import com.example.api.dto.DadosAtualizarPaciente;
import com.example.api.dto.DadosCadastroPaciente;
import com.example.api.dto.DadosDetalhamentoPaciente;
import com.example.api.dto.DadosListagemPaciente;
import com.example.api.model.Paciente;
import com.example.api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repo;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listarPacientes(@PageableDefault(sort={"nome"}, size=3) Pageable pagination){
        var page = repo.findAllByAtivoTrue(pagination).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarPaciente(@PathVariable Long id){
        var paciente = repo.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid DadosCadastroPaciente paciente, UriComponentsBuilder uriBuilder){
        var pacienteObj = new Paciente(paciente);
        repo.save(pacienteObj);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(pacienteObj));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarInformacoes(@RequestBody @Valid DadosAtualizarPaciente dados){
        var paciente = repo.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var paciente = repo.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

}
