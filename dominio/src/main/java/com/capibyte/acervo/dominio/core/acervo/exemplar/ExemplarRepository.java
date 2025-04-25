package com.capibyte.acervo.dominio.core.acervo.exemplar;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository {
    void salvar(Exemplar exemplar);

    Exemplar buscarPorId(ExemplarId exemplarId);

    List<Exemplar> buscarPorIsbn(String isbn);
}
