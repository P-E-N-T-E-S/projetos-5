package com.capibyte.acervo.dominio.core.acervo.exemplar;

import java.util.List;

public interface ExemplarRepository {
    void salvar(Exemplar exemplar);

    Exemplar buscarPorId(CodigoDaObra codigoDaObra);

    List<Exemplar> buscarPorIsbn(String isbn);

    void deletar(CodigoDaObra id);
}
