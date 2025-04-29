package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.UsuarioService;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    private ExemplarService exemplarService;

    private UsuarioService usuarioService;

    private SolicitacaoService solicitacaoService;

    public EmprestimoService(ExemplarService exemplarService, UsuarioService usuarioService, SolicitacaoService solicitacaoService) {
        this.exemplarService = exemplarService;
        this.usuarioService = usuarioService;
        this.solicitacaoService = solicitacaoService;
    }

    public void realizarEmprestimo(ExemplarId exemplarId, Matricula tomador){
        Exemplar exemplar = exemplarService.buscarPorId(exemplarId);
        Usuario tomadorUser = usuarioService.buscarPorMatricula(tomador.toString());
        exemplar.alugar(tomador, tomadorUser.getCargo());
        exemplarService.salvar(exemplar);
    }

    public void ValidarSolicitacao(Solicitacao solicitacao){
        for (ExemplarId exemplarId : solicitacao.getExemplares()){
            realizarEmprestimo(exemplarId, solicitacao.getTomador());

        }
        solicitacaoService.deletarSolicitacao(solicitacao);
    }
}
