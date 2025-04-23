package com.capibyte.acervo.dominio.core.acervo.livro;

import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;

public class Livro {
    private final Isbn isbn;
    private final String titulo;
    private AutorId autor;
    private String descricao;

    public Livro(Isbn isbn, String titulo, AutorId autor, String descricao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public AutorId getAutor() {
        return autor;
    }

    public String getDescricao() {
        return descricao;
    }
}
