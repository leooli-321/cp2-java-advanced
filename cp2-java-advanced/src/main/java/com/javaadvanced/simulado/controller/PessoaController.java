package com.javaadvanced.simulado.controller;

import com.javaadvanced.simulado.entity.Fone;
import com.javaadvanced.simulado.entity.Pessoa;
import com.javaadvanced.simulado.repository.FoneRepository;
import com.javaadvanced.simulado.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FoneRepository foneRepository;

    // Criação de Pessoa (POST)
    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

    // Obter Pessoa por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.isPresent() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    // Atualização de Pessoa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        if (pessoaRepository.existsById(id)) {
            pessoa.setId(id);  // Ajuste da ID sem necessidade de casting
            Pessoa updatedPessoa = pessoaRepository.save(pessoa);
            return ResponseEntity.ok(updatedPessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Exclusão de Pessoa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obter todas as Pessoas (GET)
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return ResponseEntity.ok(pessoas);
    }

    // Consulta JPQL por nome
    @GetMapping("/findByNome/{nome}")
    public ResponseEntity<List<Pessoa>> getByNome(@PathVariable String nome) {
        List<Pessoa> pessoas = (List<Pessoa>) pessoaRepository.findByNome(nome);
        return ResponseEntity.ok(pessoas);
    }

    // Consulta SQL Nativa por nome
    @GetMapping("/findByNomeNative/{nome}")
    public ResponseEntity<List<Pessoa>> getByNomeNative(@PathVariable String nome) {
        List<Pessoa> pessoas = (List<Pessoa>) pessoaRepository.findByNomeNative(nome);
        return ResponseEntity.ok(pessoas);
    }

    // Consulta paginada de Fones por Pessoa (GET)
    @GetMapping("/{id}/fones")
    public ResponseEntity<Page<Fone>> getFonesPaginados(@PathVariable Long id, Pageable pageable) {
        Page<Fone> fones = (Page<Fone>) foneRepository.findByPessoaId(id, pageable);
        return ResponseEntity.ok(fones);
    }
}
