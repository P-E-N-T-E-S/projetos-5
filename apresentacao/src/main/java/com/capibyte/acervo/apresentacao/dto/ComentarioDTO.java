package com.capibyte.acervo.apresentacao.dto;

import com.capibyte.acervo.dominio.core.acervo.livro.Livro;

import java.time.LocalDateTime;

public class ComentarioDTO {
    public int id;
    public String isbn;
    public String conteudo;
    public String nomeUsuario;
    public String cargoUsuario;
    public LocalDateTime dataCriacao;

    public ComentarioDTO(int id, String isbn, String conteudo, String nomeUsuario,
                         String cargoUsuario, LocalDateTime dataCriacao) {
        this.id = id;
        this.isbn = isbn;
        this.conteudo = conteudo;
        this.nomeUsuario = nomeUsuario;
        this.cargoUsuario = cargoUsuario;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
