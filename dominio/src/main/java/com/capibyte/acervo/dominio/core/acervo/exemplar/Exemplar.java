package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.exemplar.enums.Status;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Periodo;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.dominio.core.compartilhado.DataUtil;

import java.time.LocalDate;

public class Exemplar {
    private CodigoDaObra codigoDaObra;
    private Isbn livro;
    private Localizacao localizacao;
    private Emprestimo emprestimo;
    private Status status;
    private Double valor;

    public Exemplar(CodigoDaObra codigoDaObra, Isbn livro, Localizacao localizacao, Emprestimo emprestimo, Status status, Double valor) {
        this.codigoDaObra = codigoDaObra;
        this.livro = livro;
        this.localizacao = localizacao;
        this.emprestimo = emprestimo;
        this.status = status;
        this.valor = valor;
    }

    public Exemplar(CodigoDaObra codigoDaObra, Isbn livro, Localizacao localizacao) {
        this.codigoDaObra = codigoDaObra;
        this.livro = livro;
        this.localizacao = localizacao;
        this.status = Status.DISPONIVEL;
    }

    public CodigoDaObra getCodigoDaObra() {
        return codigoDaObra;
    }

    public void setCodigoDaObra(CodigoDaObra codigoDaObra) {
        this.codigoDaObra = codigoDaObra;
    }

    public Isbn getLivro() {
        return livro;
    }

    public void setLivro(Isbn livro) {
        this.livro = livro;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public String isAlugado() {
        if (this.status == Status.EMPRESTADO){
            return "Livro Reservado";
        }else{
            return "Livro Disponivel";
        }
    }

    public void alugar(Matricula tomador, Cargo cargo) {
        Periodo periodo = new Periodo(LocalDate.now(), DataUtil.adicionarDiasUteis(LocalDate.now(), cargo.diasPermitidos()));
        this.emprestimo = new Emprestimo(periodo, tomador);
        this.status = Status.EMPRESTADO;
    }
}
