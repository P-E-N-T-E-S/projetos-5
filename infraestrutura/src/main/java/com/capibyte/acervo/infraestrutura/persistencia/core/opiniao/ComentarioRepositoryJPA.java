package com.capibyte.acervo.infraestrutura.persistencia.core.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositoryJPA extends JpaRepository<ComentarioJPA, Integer> {
    List<ComentarioJPA> findByLivro_Isbn(String isbn);
}
