package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoRepository {

    void salvar(Solicitacao solicitacao);

    List<Solicitacao> obterTodas();

    Solicitacao buscarPorId(Long codigo);

    void deletar(Solicitacao solicitacao);
}
