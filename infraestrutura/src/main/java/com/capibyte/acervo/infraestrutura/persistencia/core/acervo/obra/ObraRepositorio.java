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

    @Query("SELECT o FROM ObraJPA o WHERE LOWER(o.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<ObraJPA> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    @Query("SELECT o FROM ObraJPA o JOIN o.autoresObra a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<ObraJPA> findByAutorNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor);

    @Query("SELECT o FROM ObraJPA o WHERE o.dataPublicacao BETWEEN :dataInicio AND :dataFim")
    List<ObraJPA> findByDataPublicacaoBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
