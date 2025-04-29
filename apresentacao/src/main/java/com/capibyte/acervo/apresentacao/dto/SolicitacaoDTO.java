package com.capibyte.acervo.apresentacao.dto;

import java.util.List;

public record SolicitacaoDTO(List<Long> exemplares, String tomador) {
}
