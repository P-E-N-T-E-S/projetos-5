package com.capibyte.acervo.dominio.core.administracao.solicitacao;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import java.time.LocalDate;

public class Solicitacao {
    private Matricula tomador;
    private LocalDate diaSolicitacao; //TODO: Saber se a pessoa pode reservar mais do que um livro de uma vez
    private ExemplarId exemplar;

    public Solicitacao(Matricula tomador, ExemplarId exemplar) {
        this.tomador = tomador;
        this.diaSolicitacao = LocalDate.now();
        this.exemplar = exemplar;
    }

    public Matricula getTomador() {
        return tomador;
    }

    public LocalDate getDiaSolicitacao() {
        return diaSolicitacao;
    }

    public ExemplarId getExemplar() {
        return exemplar;
    }
}
