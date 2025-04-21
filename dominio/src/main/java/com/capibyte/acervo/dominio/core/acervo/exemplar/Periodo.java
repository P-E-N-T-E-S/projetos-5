package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.exemplar.exceptions.PeriodoInvalidoException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Periodo {
    Date inicio;
    Date fim;

    public Periodo(Date inicio, Date fim) throws PeriodoInvalidoException {
        if (inicio.after(fim)) {
            throw new PeriodoInvalidoException("Data de inicio deve ser anterior a data de fim");
        }
        this.inicio = inicio;
        this.fim = fim;
    }

    public Date getInicio() {
        return inicio;
    }
    public Date getFim() {
        return fim;
    }

    public Boolean verificarValidade () {
        return !fim.after(new Date());
    }

    public long verificarDiasFaltantes() {
        LocalDate hoje = LocalDate.now();
        return ChronoUnit.DAYS.between(hoje, fim.toInstant());
    }
}
