package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Periodo;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.dominio.core.compartilhado.DataUtil;

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

    public void alugar(Matricula tomador, Cargo cargo) {
        Periodo periodo = new Periodo(LocalDate.now(), DataUtil.adicionarDiasUteis(LocalDate.now(), cargo.diasPermitidos()));
        this.emprestimo = new Emprestimo(periodo, tomador);
    }
}
