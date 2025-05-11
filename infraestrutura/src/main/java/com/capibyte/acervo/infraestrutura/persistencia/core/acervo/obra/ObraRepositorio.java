package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import java.util.List;

public interface ObraRepositorio extends JpaRepository<ObraJPA, String> {

    @Query("SELECT l FROM ObraJPA l JOIN l.palavrasChave t WHERE t = :palavraChave")
    List<ObraJPA> findByPalavraChave(@Param("palavraChave") String palavraChave);

    List<ObraJPA> findByValidado(boolean validado);
}
