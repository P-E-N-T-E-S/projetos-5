package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.ExemplarJPA;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Livro")
public class LivroJPA {

    @Id
    private String isbn;
    private String titulo;
    @ManyToMany
    @JoinTable(
            name = "autor_livro",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorJPA> autores;
    private String sinopse;
    private String numeroChamada;
    private int anoDePublicacao;
    private int quantidadeDePaginas;
    @ElementCollection
    @CollectionTable(name = "livro_temas", joinColumns = @JoinColumn(name = "isbn"))
    @Column(name = "tema")
    private List<String> temas;
    @OneToMany(mappedBy = "livro")
    private List<ExemplarJPA> exemplares;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorJPA> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorJPA> autores) {
        this.autores = autores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
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

    public List<ExemplarJPA> getExemplares() {
        return exemplares;
    }

    public void setExemplares(List<ExemplarJPA> exemplares) {
        this.exemplares = exemplares;
    }
}