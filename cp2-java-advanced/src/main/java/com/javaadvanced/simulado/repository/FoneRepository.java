package com.javaadvanced.simulado.repository;

import com.javaadvanced.simulado.entity.Fone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoneRepository extends JpaRepository<Fone, Long> { // Alterado o tipo do ID para Long

    // Exemplo de consulta JPQL simples para retornar todos os fones
    @Query("SELECT f FROM Fone f")
    List<Fone> findAllFones();

    // Consulta nativa para buscar fones por um parâmetro específico
    @Query(value = "SELECT * FROM fones f WHERE f.id = :param1", nativeQuery = true)
    List<Fone> findFonesByParam(Long param1);

    // Método para encontrar todos os fones relacionados a uma pessoa pelo ID da pessoa
    List<Fone> findByPessoaId(Long pessoaId, Pageable pageable);
}
