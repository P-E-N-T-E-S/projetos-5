package com.capibyte.acervo.dominio.core.administracao.usuario;

import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;

public class Usuario {
    private final Matricula matricula;
    private final String nome;
    private final String email;
    private final String senha;
    private final Cargo cargo;

    public Usuario(Matricula matricula, String nome, String email, String senha, Cargo cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public String getEmail() {
        return email;
    }
}
