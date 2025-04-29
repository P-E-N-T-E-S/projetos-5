package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    private SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public List<Solicitacao> listarSolicitacoes(){
        return solicitacaoRepository.obterTodas();
    }

    public void deletarSolicitacao(Solicitacao solicitacao){
        solicitacaoRepository.deletar(solicitacao);
    }

    public void salvarSolicitacao(Solicitacao solicitacao){
        solicitacaoRepository.salvar(solicitacao);
    }
}
