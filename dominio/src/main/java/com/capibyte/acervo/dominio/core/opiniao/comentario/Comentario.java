package com.capibyte.acervo.dominio.core.opiniao.comentario;

import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comentario {
    private final ComentarioId id;
    private final Livro isbn;
    private final String conteudo;
    private final LocalDateTime dataCriacao;
    private final Usuario usuario;

    public Comentario(ComentarioId id, Livro isbn, Usuario usuario,LocalDateTime dataCriacao, String conteudo) throws Exception {
        this.id = id;
        this.isbn = isbn;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
        this.conteudo = conteudo;
    }

    public static Comentario criar(Livro livro, Usuario usuario, String conteudo) throws Exception {
        return new Comentario(
                ComentarioId.gerar(),
                livro,
                usuario,
                LocalDateTime.now(),
                validarConteudo(conteudo)
        );
    }

    private static String validarConteudo(String conteudo) throws Exception {
        Objects.requireNonNull(conteudo, "Conteúdo não pode ser nulo");

        String conteudoTrimmed = conteudo.trim();

        if (conteudoTrimmed.isEmpty()) {
            throw new Exception("Conteúdo do comentário não pode ser vazio");
        }

        if (conteudoTrimmed.length() > 1000) {
            throw new Exception("Comentário não pode exceder 1000 caracteres");
        }
        return conteudoTrimmed;
    }
}
