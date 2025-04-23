package com.capibyte.acervo.persistencia.core.acervo.exemplar;

import com.capibyte.acervo.persistencia.core.acervo.livro.LivroJPA;
import jakarta.persistence.*;

@Entity
public class ExemplarJPA {

    @Id
    private long exemplarId;
    @ManyToOne
    @JoinColumn(name = "livro_isbn")
    private LivroJPA livro;
    private String localizacao;
    //private Emprestimo emprestimo;
}
