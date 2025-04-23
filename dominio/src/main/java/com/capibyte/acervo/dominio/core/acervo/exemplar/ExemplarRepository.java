package com.capibyte.acervo.dominio.core.acervo.exemplar;

import java.util.List;

public interface ExemplarRepository {
    void salvar(Exemplar exemplar);

    Exemplar buscarPorId(ExemplarId exemplarId);

    List<Exemplar> buscarPorIsbn();
}
