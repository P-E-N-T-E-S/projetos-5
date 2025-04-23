package com.capibyte.acervo.dominio.core.administracao.solicitacao;

import com.capibyte.acervo.dominio.core.acervo.exemplar.EmprestimoService;
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

    public void ValidarSolicitacao(Solicitacao solicitacao){ //TODO: perguntar a saulo se precisamos de um dominio de envio de emails
        emprestimoService.realizarEmprestimo(solicitacao.getExemplar(), solicitacao.getTomador());
        solicitacaoRepository.deletar(solicitacao);
    }
}
