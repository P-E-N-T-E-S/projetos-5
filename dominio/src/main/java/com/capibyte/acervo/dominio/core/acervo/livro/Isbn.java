package com.capibyte.acervo.dominio.core.acervo.livro;

public class Isbn {
    private final String codigo;

    public Isbn(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
