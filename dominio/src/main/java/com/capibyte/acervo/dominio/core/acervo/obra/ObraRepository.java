package com.capibyte.acervo.dominio.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;

public interface ObraRepository {
    void salvar(Obra obra);
    Obra buscarPorId(DOI doi);
    void deletar(DOI doi);
}
