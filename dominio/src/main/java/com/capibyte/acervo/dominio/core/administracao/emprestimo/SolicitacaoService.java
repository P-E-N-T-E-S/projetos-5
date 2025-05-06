package com.capibyte.acervo.dominio.core.administracao.emprestimo;

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

    public Solicitacao buscarSolicitacaoPorId(Long codigo){
        return solicitacaoRepository.buscarPorId(codigo);
    }

    public void deletarSolicitacao(Long id){
        Solicitacao solicitacao = buscarSolicitacaoPorId(id);
        solicitacaoRepository.deletar(solicitacao);
    }

    public void salvarSolicitacao(Solicitacao solicitacao){
        solicitacaoRepository.salvar(solicitacao);
    }
}
