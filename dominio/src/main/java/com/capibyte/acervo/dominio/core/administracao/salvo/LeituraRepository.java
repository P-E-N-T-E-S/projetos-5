package com.capibyte.acervo.dominio.core.administracao.salvo;

import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import java.util.List;

public interface LeituraRepository {

    void salvar(ListaLeitura leitura);
    ListaLeitura buscarPorID(ListaId id);
    void excluirPorID(ListaId id);
    List<ListaLeitura> listarPorAluno(Matricula aluno);
}
