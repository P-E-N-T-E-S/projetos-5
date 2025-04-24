package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.exceptions.PeriodoInvalidoException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Periodo {
    LocalDate inicio;
    LocalDate fim;

    public Periodo(LocalDate inicio, LocalDate fim) throws PeriodoInvalidoException {
        if (inicio.isAfter(fim)) {
            throw new PeriodoInvalidoException("Data de inicio deve ser anterior a data de fim");
        }
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDate getInicio() {
        return inicio;
    }
    public LocalDate getFim() {
        return fim;
    }

    public Boolean verificarValidade () {
        return !fim.isAfter(LocalDate.now());
    }

    public long verificarDiasFaltantes() {
        LocalDate hoje = LocalDate.now();
        return ChronoUnit.DAYS.between(hoje, fim);
    }
}
