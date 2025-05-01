
package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SolicitacaoImpl implements SolicitacaoRepository {

    private final SolicitacaoRepositorioJPA repositorioJPA;
    private final JpaMapeador mapeador;

    public SolicitacaoImpl(SolicitacaoRepositorioJPA repositorioJPA, JpaMapeador mapeador) {
        this.repositorioJPA = repositorioJPA;
        this.mapeador = mapeador;
    }

    @Override
    public void salvar(Solicitacao solicitacao) {
        SolicitacaoJPA entidade = mapeador.map(solicitacao, SolicitacaoJPA.class);
        repositorioJPA.save(entidade);
    }

    @Override
    public List<Solicitacao> obterTodas() {
        return repositorioJPA.findAll().stream()
                .map(jpa -> mapeador.map(jpa, Solicitacao.class))
                .collect(Collectors.toList());
    }

    @Override
    public Solicitacao buscarPorId(Long codigo) {
        SolicitacaoJPA jpa = repositorioJPA.findById(codigo).orElse(null);
        return mapeador.map(jpa, Solicitacao.class);
    }

    @Override
    public void deletar(Solicitacao solicitacao) {
        SolicitacaoJPA jpa = repositorioJPA.findByMatricula(solicitacao.getTomador().toString());
        if (jpa != null) {
            repositorioJPA.delete(jpa);
        }
    }
}