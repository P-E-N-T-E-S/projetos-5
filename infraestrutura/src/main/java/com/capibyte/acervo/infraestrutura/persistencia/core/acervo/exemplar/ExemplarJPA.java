package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Status;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.EmprestimoJPA;
import jakarta.persistence.*;

@Entity
@Table(name = "Exemplar")
public class ExemplarJPA {

    @Id
    private long codigoDaObra;

    @ManyToOne
    @JoinColumn(name = "livro_isbn")
    private LivroJPA livro;

    @Embedded
    private LocalizacaoJpa localizacao;

    @Embedded
    private EmprestimoJPA emprestimo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DISPONIVEL;

    public long getCodigoDaObra() {
        return codigoDaObra;
    }

    public LivroJPA getLivro() {
        return livro;
    }

    public LocalizacaoJpa getLocalizacao() {
        return localizacao;
    }

    public EmprestimoJPA getEmprestimo() {
        return emprestimo;
    }

    public Status getStatus() {
        return status;
    }

    public void setCodigoDaObra(long exemplarId) {
        this.codigoDaObra = exemplarId;
    }

    public void setLivro(LivroJPA livro) {
        this.livro = livro;
    }

    public void setLocalizacao(LocalizacaoJpa localizacao) {
        this.localizacao = localizacao;
    }

    public void setEmprestimo(EmprestimoJPA emprestimo) {
        this.emprestimo = emprestimo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}