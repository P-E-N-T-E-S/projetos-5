package com.capibyte.acervo.apresentacao.dto;

import java.time.LocalDate;
import java.util.List;

public class ObraDetalhadaDTO {
    private String doi;
    private String titulo;
    private List<AutorDTO> autores;
    private List<String> palavrasChave;
    private String resumo;
    private LocalDate dataPublicacao;
    private String citacaoAbnt;

    public ObraDetalhadaDTO(String doi, String titulo, List<AutorDTO> autores, List<String> palavrasChave,
                            String resumo, LocalDate dataPublicacao, String citacaoAbnt) {
        this.doi = doi;
        this.titulo = titulo;
        this.autores = autores;
        this.palavrasChave = palavrasChave;
        this.resumo = resumo;
        this.dataPublicacao = dataPublicacao;
        this.citacaoAbnt = citacaoAbnt;
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

    public String getDoi() {
        return doi;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public String getResumo() {
        return resumo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getCitacaoAbnt() {
        return citacaoAbnt;
    }
}

