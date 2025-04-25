package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplarRepositorio extends JpaRepository<ExemplarJPA, Long> {
    List<ExemplarJPA> findByLivroIsbn(String isbn);
}
