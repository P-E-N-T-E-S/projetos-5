package com.capibyte.acervo.dominio.core.administracao.usuario;

public class Usuario {
    private final
    private final String nome;
    private final String email;
    private final String senha;
    private final boolean isProfessor;

    public Usuario(String nome, String email, String senha, boolean isProfessor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isProfessor = isProfessor;
    }
}
