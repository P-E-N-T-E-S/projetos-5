package com.capibyte.acervo.dominio.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;

import java.time.LocalDate;
import java.util.List;

public class Obra { //obra digital, tcc
    private DOI doi;
    private String titulo;
    private List<AutorId> autores;
    private List<PalavraChave> palavrasChave;
    private String resumo;
    private LocalDate dataPublicacao;
    private String citacaoAbnt;

    public Obra(DOI doi, String titulo, List<AutorId> autores, List<PalavraChave> palavrasChave, String resumo, LocalDate dataPublicacao, String citacaoAbnt) {
        this.doi = doi;
        this.titulo = titulo;
        this.autores = autores;
        this.palavrasChave = palavrasChave;
        this.resumo = resumo;
        this.dataPublicacao = dataPublicacao;
        this.citacaoAbnt = citacaoAbnt;
    }

    public DOI getDoi() {
        return doi;
    }

    public void setDoi(DOI doi) {
        this.doi = doi;
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

    public List<PalavraChave> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<PalavraChave> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getCitacaoAbnt() {
        return citacaoAbnt;
    }

    public void setCitacaoAbnt(String citacaoAbnt) {
        this.citacaoAbnt = citacaoAbnt;
    }
}
