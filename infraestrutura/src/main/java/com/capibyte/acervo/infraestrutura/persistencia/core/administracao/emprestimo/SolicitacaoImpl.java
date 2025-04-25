
package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoRepository;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public void salvar(Matricula tomador, ExemplarId exemplarId) {
        SolicitacaoJPA solicitacaoJPA = repositorioJPA.findByMatricula(tomador.toString());

        if (solicitacaoJPA == null) {
            solicitacaoJPA = new SolicitacaoJPA();
            solicitacaoJPA.setMatricula(tomador.toString());
            solicitacaoJPA.setExemplarIds(new ArrayList<>());
        }

        solicitacaoJPA.getExemplarIds().add(exemplarId.getId());
        repositorioJPA.save(solicitacaoJPA);
    }

    @Override
    public List<Solicitacao> obterTodas() {
        return repositorioJPA.findAll().stream()
                .map(jpa -> mapeador.map(jpa, Solicitacao.class))
                .collect(Collectors.toList());
    }

    @Override
    public Solicitacao buscarPorMatricula(Matricula tomador) {
        SolicitacaoJPA jpa = repositorioJPA.findByMatricula(tomador.toString());
        return jpa != null ? mapeador.map(jpa, Solicitacao.class) : null;
    }

    @Override
    public void deletar(Solicitacao solicitacao) {
        SolicitacaoJPA jpa = repositorioJPA.findByMatricula(solicitacao.getTomador().toString());
        if (jpa != null) {
            repositorioJPA.delete(jpa);
        }
    }
}