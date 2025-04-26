package com.capibyte.acervo.apresentacao.dto;

import java.time.LocalDateTime;

public record ComentarioDTO(int id, String isbn, String conteudo, String nomeUsuario,
                            String cargoUsuario, LocalDateTime dataCriacao) {
}
