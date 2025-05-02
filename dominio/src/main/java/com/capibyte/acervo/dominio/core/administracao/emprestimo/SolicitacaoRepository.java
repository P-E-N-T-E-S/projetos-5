package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoRepository {

    void salvar(Solicitacao solicitacao);

    List<Solicitacao> obterTodas();

    Solicitacao buscarPorId(Long codigo);

    void deletar(Solicitacao solicitacao);
}
