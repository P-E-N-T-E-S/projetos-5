package com.capibyte.acervo.apresentacao.dto;

import java.util.List;

public class LivroDetalhadoDTO {
    private String isbn;
    private String titulo;
    private List<AutorDTO> autores;
    private String sinopse;
    private String numeroChamada;
    private int anoDePublicacao;
    private int quantidadeDePaginas;
    private List<String> temas;

    public LivroDetalhadoDTO(String isbn, String titulo, List<AutorDTO> autores, String sinopse,
                             String numeroChamada, int anoDePublicacao, int quantidadeDePaginas,
                             List<String> temas) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.sinopse = sinopse;
        this.numeroChamada = numeroChamada;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.temas = temas;
    }

    public static class AutorDTO {
        private Long id;
        private String nome;

        public AutorDTO(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getNumeroChamada() {
        return numeroChamada;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public List<String> getTemas() {
        return temas;
    }
}
