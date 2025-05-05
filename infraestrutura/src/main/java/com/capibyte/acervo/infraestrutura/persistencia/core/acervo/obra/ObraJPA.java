package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ObraJPA {

    @Id
    private String doi;
    private String titulo;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "autor_obra",
            joinColumns = @JoinColumn(name = "doi"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorJPA> autoresObra;
    @ElementCollection
    @CollectionTable(
            name = "obras_temas",
            joinColumns = @JoinColumn(name = "doi")
    )
    @Column(name = "palavrasChave")
    private List<String> palavrasChave;
    private String resumo;
    private LocalDate dataPublicacao;
    private String citacaoAbnt;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorJPA> getAutoresObra() {
        return autoresObra;
    }

    public void setAutoresObra(List<AutorJPA> autoresObra) {
        this.autoresObra = autoresObra;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
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
