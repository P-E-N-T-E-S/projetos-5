package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObraRepositorio extends JpaRepository<ObraJPA, String> {

    @Query("SELECT l FROM ObraJPA l JOIN l.palavrasChave t WHERE t = :palavraChave")
    List<LivroJPA> findByPalavraChave(@Param("palavraChave") String palavraChave);
}
