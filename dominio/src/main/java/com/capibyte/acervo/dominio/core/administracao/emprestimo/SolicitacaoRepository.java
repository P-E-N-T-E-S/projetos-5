package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import java.util.List;

public interface SolicitacaoRepository {

    void salvar(Matricula tomador, ExemplarId exemplarId);

    List<Solicitacao> obterTodas();

    //TODO: Talvez seja List<> depende se uma pessoa pode ter mais de um livro alugado por vêz
    Solicitacao buscarPorMatricula(Matricula tomador);

    void deletar(Solicitacao solicitacao);
}
