package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.salvo;

import com.capibyte.acervo.dominio.core.administracao.salvo.LeituraRepository;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaId;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaLeitura;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListaLeituraImpl implements LeituraRepository {

    private ListaRepositorio repository;
    private JpaMapeador mapeador;

    public ListaLeituraImpl(ListaRepositorio repository, JpaMapeador mapeador) {
        this.repository = repository;
        this.mapeador = mapeador;
    }

    @Override
    public void salvar(ListaLeitura leitura) {
        ListaLeituraJPA entidade = mapeador.map(leitura, ListaLeituraJPA.class);
        repository.save(entidade);
    }

    @Override
    public ListaLeitura buscarPorID(ListaId id) {
        ListaLeituraJPA entidade = repository.findById(id.getCodigo()).orElse(null);
        return mapeador.map(entidade, ListaLeitura.class);
    }

    @Override
    public void excluirPorID(ListaId id) {
        ListaLeituraJPA entidade = repository.findById(id.getCodigo()).orElse(null);
        assert entidade != null;
        repository.delete(entidade);
    }

    @Override
    public List<ListaLeitura> listarPorAluno(Matricula aluno) {
        return repository.findManyByUsuarioMatricula(aluno.toString()).stream().map(jpa -> mapeador.map(jpa, ListaLeitura.class)).toList();
    }
}
