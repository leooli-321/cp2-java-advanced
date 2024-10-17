package com.javaadvanced.simulado.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Definindo herança no JPA
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fone> fones;
}
