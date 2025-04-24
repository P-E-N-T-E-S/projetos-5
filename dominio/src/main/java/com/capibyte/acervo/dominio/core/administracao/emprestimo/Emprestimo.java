package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import static org.apache.commons.lang3.Validate.notNull;

public class Emprestimo {
    private Periodo periodo;
    private Matricula tomador;

    public Emprestimo(Periodo periodo, Matricula tomador) {
        notNull(tomador, "O tomador não pode ser nulo");
        notNull(periodo, "O periodo não pode ser nulo");
        this.periodo = periodo;
        this.tomador = tomador;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Matricula getTomador() {
        return tomador;
    }
}
