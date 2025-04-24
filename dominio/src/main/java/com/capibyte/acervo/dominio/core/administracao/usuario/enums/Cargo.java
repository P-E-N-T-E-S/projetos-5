package com.capibyte.acervo.dominio.core.administracao.usuario.enums;

public enum Cargo {
    GRADUANDO(0, "Aluno"),
    PROFESSOR(1, "Professor"),
    MESTRADO(2, "Mestrado"),
    BIBLIOTECARIA(3, "Bibliotecaria");

    private long identificador;
    private String titulo;

    Cargo(long identificador, String titulo) {
        this.identificador = identificador;
        this.titulo = titulo;
    }

    public long getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public static Cargo fromIdentificador(long id) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.getIdentificador() == id) {
                return cargo;
            }
        }
        throw new IllegalArgumentException("Cargo não encontrado para o id: " + id);
    }

    public int diasPermitidos() {
        return switch (this) {
            case GRADUANDO -> 7;
            case MESTRADO -> 15;
            case PROFESSOR -> 30;
            case BIBLIOTECARIA -> 0;
        };
    }
}
