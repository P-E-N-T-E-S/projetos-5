package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<LivroJPA, String> {
    @Query("SELECT l FROM LivroJPA l JOIN l.autoresLivro a WHERE a.id = :autorId")
    List<LivroJPA> findByAutorId(@Param("autorId") Long autorId);
    LivroJPA findByIsbn(String isbn);
    @Query("SELECT l FROM LivroJPA l JOIN l.temas t WHERE t = :tema")
    List<LivroJPA> findByTema(@Param("tema") String tema);

    @Query("SELECT l FROM LivroJPA l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<LivroJPA> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    @Query("SELECT l FROM LivroJPA l JOIN l.autoresLivro a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<LivroJPA> findByAutorNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor);

    @Query("SELECT l FROM LivroJPA l WHERE l.anoDePublicacao BETWEEN :anoInicio AND :anoFim")
    List<LivroJPA> findByAnoDePublicacaoBetween(@Param("anoInicio") int anoInicio, @Param("anoFim") int anoFim);

    @Query("SELECT l FROM LivroJPA l WHERE l.numeroChamada LIKE CONCAT('%', :numeroChamada, '%')")
    List<LivroJPA> findByNumeroChamadaContaining(@Param("numeroChamada") String numeroChamada);
}
