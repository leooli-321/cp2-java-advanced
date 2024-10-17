package com.javaadvanced.simulado.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PessoaJuridica extends Pessoa {

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;
}
