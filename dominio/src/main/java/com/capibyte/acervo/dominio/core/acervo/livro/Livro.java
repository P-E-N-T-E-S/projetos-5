package com.capibyte.acervo.dominio.core.acervo.livro;

public class Livro {
    private final Isbn isbn;
    private final String titulo;

    private Livro(Isbn isbn, String titulo) {
        this.isbn = isbn;
        this.titulo = titulo;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }
}
