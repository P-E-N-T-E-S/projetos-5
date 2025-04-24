package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Usuario")
public class UsuarioJPA {
    @Id
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private int cargo;

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getCargo() {
        return cargo;
    }
}
