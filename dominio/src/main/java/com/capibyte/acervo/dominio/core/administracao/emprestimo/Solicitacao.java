package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;

import java.time.LocalDate;
import java.util.List;

public class Solicitacao {
    private Matricula tomador;
    private LocalDate diaSolicitacao;
    private List<ExemplarId> exemplares;
    private Cargo cargo;

    public Solicitacao(Matricula tomador, LocalDate diaSolicitacao, List<ExemplarId> exemplares, Cargo cargo) {
        this.tomador = tomador;
        this.diaSolicitacao = diaSolicitacao;
        this.exemplares = exemplares;
        this.cargo = cargo;
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

    public Cargo getCargo() {
        return cargo;
    }
}
