package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Periodo;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;

import java.time.LocalDate;

public class Exemplar {
    private ExemplarId exemplarId;
    private Isbn livro;
    private String localizacao;
    private Emprestimo emprestimo;

    public Exemplar(ExemplarId exemplarId, Isbn livro, String localizacao, Emprestimo emprestimo) {
        this.exemplarId = exemplarId;
        this.livro = livro;
        this.localizacao = localizacao;
        this.emprestimo = emprestimo;
    }

    public ExemplarId getExemplarId() {
        return exemplarId;
    }

    public Isbn getLivro() {
        return livro;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public boolean isAlugado() {
        return emprestimo != null;
    }

    public void alugar(Matricula tomador) {
        Periodo periodo = new Periodo(LocalDate.now(), LocalDate.now().plusDays(7)); //TODO: Descobrir se existe um padrão de dias de empréstimos ou se tem q ser settavel
        this.emprestimo = new Emprestimo(periodo, tomador);
    }
}
