package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepositorio extends JpaRepository<LivroJPA, String> {

    LivroJPA findByIsbn(String isbn);
}
