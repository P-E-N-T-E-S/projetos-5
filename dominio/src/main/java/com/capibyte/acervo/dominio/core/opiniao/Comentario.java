package com.capibyte.acervo.dominio.core.opiniao;

import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comentario {
    private final ComentarioId id;
    private final Livro isbn;
    private final String conteudo;
    private final LocalDateTime dataCriacao;
    private final Usuario usuario;

    public Comentario(ComentarioId id, Livro isbn, Usuario usuario, String conteudo, LocalDateTime dataCriacao) throws Exception {
        this.id = id;
        this.isbn = isbn;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
    }

    public static Comentario criar(Livro livro, Usuario usuario, String conteudo) throws Exception {
        return new Comentario(
                ComentarioId.gerar(),
                livro,
                usuario,
                validarConteudo(conteudo),
                LocalDateTime.now()
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

    public int getId() {
        return id.getId();
    }

    public Livro getIsbn() {
        return isbn;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
