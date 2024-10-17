package com.javaadvanced.simulado.repository;

import com.javaadvanced.simulado.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Consulta JPQL para buscar pessoas por nome (com suporte a LIKE)
    @Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %:nome%")
    Collection<Pessoa> findByNome(@Param("nome") String nome);

    // Consulta usando SQL nativo
    @Query(value = "SELECT * FROM pessoas WHERE nome = ?1", nativeQuery = true)
    Collection<Pessoa> findByNomeNative(String nome);

    // Consulta que retorna pessoas cujo nome NÃO é o fornecido (JPQL)
    Collection<Pessoa> findByNomeNot(String nome);
}
