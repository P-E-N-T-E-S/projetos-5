package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import java.time.LocalDate;
import java.util.List;

public class Solicitacao {
    private SolicitacaoId id;
    private Matricula tomador;
    private LocalDate diaSolicitacao;
    private List<CodigoDaObra> exemplares;

    public Solicitacao(SolicitacaoId id, Matricula tomador, LocalDate diaSolicitacao, List<CodigoDaObra> exemplares) {
        this.id = id;
        this.tomador = tomador;
        this.diaSolicitacao = diaSolicitacao;
        this.exemplares = exemplares;
    }

    public Solicitacao(Matricula tomador, LocalDate diaSolicitacao, List<CodigoDaObra> exemplares) {
        this.tomador = tomador;
        this.diaSolicitacao = diaSolicitacao;
        this.exemplares = exemplares;
    }

    public Matricula getTomador() {
        return tomador;
    }

    public LocalDate getDiaSolicitacao() {
        return diaSolicitacao;
    }

    public List<CodigoDaObra> getExemplares() {
        return exemplares;
    }

    public SolicitacaoId getId() {
        return id;
    }
}
