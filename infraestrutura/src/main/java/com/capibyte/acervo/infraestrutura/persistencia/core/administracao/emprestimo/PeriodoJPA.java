package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class PeriodoJPA {
    LocalDate inicio;
    LocalDate fim;

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }
}
