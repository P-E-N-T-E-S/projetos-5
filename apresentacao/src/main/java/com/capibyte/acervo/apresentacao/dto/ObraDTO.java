package com.capibyte.acervo.apresentacao.dto;

import java.time.LocalDate;
import java.util.List;

public record ObraDTO(String doi, String titulo, List<String> autores, List<String> palavrasChave, String resumo, LocalDate dataPublicacao, String citacaoAbnt, boolean validado) {
}
