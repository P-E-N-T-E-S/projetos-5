package com.capibyte.acervo.dominio.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;

import java.time.LocalDate;
import java.util.List;

public interface ObraRepository {
    void salvar(Obra obra);
    Obra buscarPorId(DOI doi);
    void deletar(DOI doi);
    List<Obra> obterTodos();
    List<Obra> buscarPorPalavraChave(String palavraChave);
    List<Obra> buscarPorValidado(boolean validado);
    List<Obra> buscarPorTitulo(String titulo);
    List<Obra> buscarPorAutor(String nomeAutor);
    List<Obra> buscarPorDataPublicacao(LocalDate dataInicio, LocalDate dataFim);
}
