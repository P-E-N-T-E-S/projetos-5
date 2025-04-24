package com.capibyte.acervo.dominio.core.compartilhado;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DataUtil {

    public static LocalDate adicionarDiasUteis(LocalDate dataInicial, int diasUteis) {
        LocalDate data = dataInicial;
        int adicionados = 0;

        while (adicionados < diasUteis) {
            data = data.plusDays(1);
            DayOfWeek diaDaSemana = data.getDayOfWeek();

            if (diaDaSemana != DayOfWeek.SATURDAY && diaDaSemana != DayOfWeek.SUNDAY) {
                adicionados++;
            }
        }

        return data;
    }
}
