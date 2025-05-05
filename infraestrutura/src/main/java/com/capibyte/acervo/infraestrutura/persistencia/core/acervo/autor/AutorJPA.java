package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra.ObraJPA;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Autor")
public class AutorJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToMany(mappedBy = "autoresLivro")
    private List<LivroJPA> livros;
    @ManyToMany(mappedBy = "autoresObra")
    private List<ObraJPA> obras;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LivroJPA> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroJPA> livros) {
        this.livros = livros;
    }

    public List<ObraJPA> getObras() {
        return obras;
    }

    public void setObras(List<ObraJPA> obras) {
        this.obras = obras;
    }
}
