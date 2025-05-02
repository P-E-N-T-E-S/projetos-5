package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<LivroJPA, String> {
    @Query("SELECT l FROM LivroJPA l JOIN l.autores a WHERE a.id = :autorId")
    List<LivroJPA> findByAutorId(@Param("autorId") Long autorId);
    LivroJPA findByIsbn(String isbn);
}
