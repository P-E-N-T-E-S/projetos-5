package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario;

import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.salvo.ListaLeituraJPA;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class UsuarioJPA {
    @Id
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private long cargo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListaLeituraJPA> listaLeitura = new ArrayList<>();

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

    public long getCargo() {
        return cargo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargo(long cargo) {
        this.cargo = cargo;
    }

    public List<ListaLeituraJPA> getListaLeitura() {
        return listaLeitura;
    }

    public void setListaLeitura(List<ListaLeituraJPA> listaLeitura) {
        this.listaLeitura = listaLeitura;
    }
}
