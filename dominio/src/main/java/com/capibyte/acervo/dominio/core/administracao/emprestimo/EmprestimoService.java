package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.UsuarioService;
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

    public void realizarEmprestimo(CodigoDaObra codigoDaObra, Matricula tomador){
        Exemplar exemplar = exemplarService.buscarPorId(codigoDaObra);
        Usuario tomadorUser = usuarioService.buscarPorMatricula(tomador.toString());
        exemplar.alugar(tomador, tomadorUser.getCargo());
        exemplarService.salvar(exemplar);
    }

    public void aprovarEmprestimo(Long id){
        Solicitacao solicitacao = solicitacaoService.buscarSolicitacaoPorId(id);
        for (CodigoDaObra codigoDaObra : solicitacao.getExemplares()){
            realizarEmprestimo(codigoDaObra, solicitacao.getTomador());

        }
        solicitacaoService.deletarSolicitacao(id);
    }

    public void recusarEmprestimo(Long id){
        solicitacaoService.deletarSolicitacao(id);
        //TODO: enviar uma mensagem para quem fez a solicitacao
    }
}
