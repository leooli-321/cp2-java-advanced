package com.javaadvanced.simulado.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fones")
public class Fone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;  // Alterado para Long, já que IDs geralmente são Long

    @Column(name = "numero", nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
}
