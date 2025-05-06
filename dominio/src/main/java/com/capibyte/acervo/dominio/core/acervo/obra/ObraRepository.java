package com.capibyte.acervo.dominio.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;

import java.util.List;

public interface ObraRepository {
    void salvar(Obra obra);
    Obra buscarPorId(DOI doi);
    void deletar(DOI doi);
    List<Obra> obterTodos();
    List<Obra> buscarPorPalavraChave(String palavraChave);
}
