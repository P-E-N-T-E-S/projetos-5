package com.capibyte.acervo.persistencia.core.acervo.exemplar;

import com.capibyte.acervo.persistencia.core.acervo.livro.LivroJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplarRepository extends JpaRepository<ExemplarJPA, Long> {
    List<ExemplarJPA> findByLivroIsbn(String isbn);
}
