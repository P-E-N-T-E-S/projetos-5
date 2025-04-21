package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;

public class Exemplar {
    private ExemplarId exemplarId;
    private Isbn livro;
    String localizacao;

    public Exemplar(ExemplarId exemplarId, Isbn livro, String localizacao) {
        this.exemplarId = exemplarId;
        this.livro = livro;
        this.localizacao = localizacao;
    }
}
