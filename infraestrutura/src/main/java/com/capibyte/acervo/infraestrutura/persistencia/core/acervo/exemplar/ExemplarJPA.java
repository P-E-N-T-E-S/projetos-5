package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.EmprestimoJPA;
import jakarta.persistence.*;

@Entity
@Table(name = "Exemplar")
public class ExemplarJPA {

    @Id
    private long exemplarId;
    @ManyToOne
    @JoinColumn(name = "livro_isbn")
    private LivroJPA livro;
    private String localizacao;
    @Embedded
    private EmprestimoJPA emprestimo;

    public long getExemplarId() {
        return exemplarId;
    }

    public LivroJPA getLivro() {
        return livro;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public EmprestimoJPA getEmprestimo() {
        return emprestimo;
    }

    public void setExemplarId(long exemplarId) {
        this.exemplarId = exemplarId;
    }

    public void setLivro(LivroJPA livro) {
        this.livro = livro;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setEmprestimo(EmprestimoJPA emprestimo) {
        this.emprestimo = emprestimo;
    }
}
