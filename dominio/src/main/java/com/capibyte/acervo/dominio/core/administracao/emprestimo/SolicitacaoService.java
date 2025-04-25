package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    private SolicitacaoRepository solicitacaoRepository;
    private EmprestimoService emprestimoService;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository, EmprestimoService emprestimoService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.emprestimoService = emprestimoService;
    }

    public List<Solicitacao> listarSolicitacoes(){
        return solicitacaoRepository.obterTodas();
    }

    public void ValidarSolicitacao(Solicitacao solicitacao){
        for (ExemplarId exemplarId : solicitacao.getExemplares()){
            emprestimoService.realizarEmprestimo(exemplarId, solicitacao.getTomador(), solicitacao.getCargo());
            
        }
        solicitacaoRepository.deletar(solicitacao);
    }
}
