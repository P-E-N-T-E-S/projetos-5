package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorJpaRepository extends JpaRepository<AutorJPA, Long> {
    Optional<AutorJPA> findById(Long id);
    @Query("SELECT a FROM AutorJPA a JOIN a.livros l WHERE l.isbn = :isbn")
    List<AutorJPA> findByLivroIsbn(@Param("isbn") String isbn);
    Optional<AutorJPA> findByNome(String nome);
}
