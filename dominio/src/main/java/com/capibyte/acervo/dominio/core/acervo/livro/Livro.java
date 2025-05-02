package com.capibyte.acervo.dominio.core.acervo.livro;

import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;

import java.util.List;

public class Livro {
    private Isbn isbn;
    private String titulo;
    private List<AutorId> autores;
    private String sinpose;
    private String numeroChamada;
    private int anoDePublicacao;
    private int quantidadeDePaginas;
    private List<String> temas;

    public Livro(Isbn isbn, String titulo, List<AutorId> autores, String sinpose, String numeroChamada, int anoDePublicacao, int quantidadeDePaginas, List<String> temas) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.sinpose = sinpose;
        this.numeroChamada = numeroChamada;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.temas = temas;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorId> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorId> autores) {
        this.autores = autores;
    }

    public String getSinpose() {
        return sinpose;
    }

    public void setSinpose(String sinpose) {
        this.sinpose = sinpose;
    }

    public String getNumeroChamada() {
        return numeroChamada;
    }

    public void setNumeroChamada(String numeroChamada) {
        this.numeroChamada = numeroChamada;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setQuantidadeDePaginas(int quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }
}
