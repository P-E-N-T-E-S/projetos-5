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
            joinColumns = @JoinColumn(name = "isbn"), //
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorJPA> autores;
    private String descricao;
    @OneToMany(mappedBy = "livro")
    private List<ExemplarJPA> exemplares;
}
