package com.capibyte.acervo.apresentacao.dto;

import java.util.List;

public record LivroDTO(String isbn, String titulo, List<String> autores, String sinopse, String numeroChamada, int anoPublicacao, int quantidadeDePaginas, List<String> temas) {
}
