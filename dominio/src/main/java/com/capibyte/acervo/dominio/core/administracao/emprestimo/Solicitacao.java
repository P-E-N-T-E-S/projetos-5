package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;

import java.time.LocalDate;
import java.util.List;

public class Solicitacao {
    private SolicitacaoId id;
    private Matricula tomador;
    private LocalDate diaSolicitacao;
    private List<ExemplarId> exemplares;

    public Solicitacao(SolicitacaoId id, Matricula tomador, LocalDate diaSolicitacao, List<ExemplarId> exemplares) {
        this.id = id;
        this.tomador = tomador;
        this.diaSolicitacao = diaSolicitacao;
        this.exemplares = exemplares;
    }

    public Solicitacao(Matricula tomador, LocalDate diaSolicitacao, List<ExemplarId> exemplares) {
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

    public List<ExemplarId> getExemplares() {
        return exemplares;
    }

    public SolicitacaoId getId() {
        return id;
    }
}
