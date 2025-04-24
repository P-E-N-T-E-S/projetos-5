package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Autor")
public class AutorJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToMany(mappedBy = "autores")
    private List<LivroJPA> livros;

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
}
