package com.capibyte.acervo.dominio.core.opiniao;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comentario {
    private ComentarioId id;
    private Isbn isbn;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private Matricula usuario;

    public Comentario(ComentarioId id, Isbn isbn, String conteudo, LocalDateTime dataCriacao, Matricula usuario) {
        this.id = id;
        this.isbn = isbn;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
    }

        Objects.requireNonNull(conteudo, "Conteúdo não pode ser nulo");
    public Comentario(Isbn isbn, String conteudo, LocalDateTime dataCriacao, Matricula usuario) {
        this.isbn = isbn;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
    }

    public ComentarioId getId() {
        return id;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Matricula getUsuario() {
        return usuario;
    }
}
