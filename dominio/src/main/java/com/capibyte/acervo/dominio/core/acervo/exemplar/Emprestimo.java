package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Emprestimo {
    private Periodo periodo;
    private Exemplar exemplar;
    private Matricula tomador;

    public Emprestimo(Periodo periodo, Exemplar exemplar, Matricula tomador) {
        notNull(exemplar, "O exemplar não pode ser nulo");
        notNull(tomador, "O tomador não pode ser nulo");
        notNull(periodo, "O periodo não pode ser nulo");
        this.periodo = periodo;
        this.exemplar = exemplar;
        this.tomador = tomador;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Matricula getTomador() {
        return tomador;
    }
}
