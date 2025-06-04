package com.capibyte.acervo.dominio.core.acervo.exemplar.enums;

public enum Status {

    DISPONIVEL(0, "Disponível"),
    EMPRESTADO(1, "Emprestado"),
    INDISPONIVEL(2, "Indisponível");

    private int id;
    private String descricao;

    Status(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status getStatus(int id) {
        for (Status status : Status.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }
}
