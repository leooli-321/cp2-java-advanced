package com.javaadvanced.simulado.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PessoaFisica extends Pessoa {

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
}
