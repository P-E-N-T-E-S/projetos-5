package com.capibyte.acervo.dominio.core.administracao.solicitacao;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

public interface SolicitacaoRepository {

    void salvar(Matricula tomador, ExemplarId exemplarId);
}
