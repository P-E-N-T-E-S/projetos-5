package com.acervo.persistencia.memoria;

import com.capibyte.acervo.dominio.core.acervo.autor.AutorService;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroService;
import com.capibyte.acervo.dominio.core.acervo.obra.ObraService;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.EmprestimoService;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoService;
import com.capibyte.acervo.dominio.core.administracao.salvo.LeituraService;
import com.capibyte.acervo.dominio.core.administracao.usuario.UsuarioService;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioService;
import com.capibyte.acervo.dominio.log.PesquisaLogService;

public class FuncionalidadesSistema{

    protected AutorService autorService;
    protected ExemplarService exemplarService;
    protected LivroService livroService;
    protected ObraService obraService;
    protected SolicitacaoService solicitacaoService;
    protected LeituraService leituraService;
    protected UsuarioService usuarioService;
    protected ComentarioService comentarioService;
    protected PesquisaLogService pesquisaLogService;
    protected EmprestimoService emprestimoService;

    public FuncionalidadesSistema(){
        var repositorio = new Repositorio();

        autorService = new AutorService(repositorio);
        exemplarService = new ExemplarService(repositorio);
        livroService = new LivroService(repositorio, pesquisaLogService);
        obraService = new ObraService(repositorio);
        solicitacaoService = new SolicitacaoService(repositorio);
        leituraService = new LeituraService(repositorio);
        usuarioService = new UsuarioService(repositorio);
        comentarioService = new ComentarioService(repositorio);
        emprestimoService = new EmprestimoService(exemplarService, usuarioService, solicitacaoService);
    }
}
