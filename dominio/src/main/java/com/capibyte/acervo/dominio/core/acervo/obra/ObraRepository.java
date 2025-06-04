package com.capibyte.acervo.dominio.core.acervo.obra;

import java.time.LocalDate;
import java.util.List;

public interface ObraRepository {
    void salvar(Obra obra);
    Obra buscarPorId(DOI doi);
    void deletar(DOI doi);
    List<Obra> obterTodosObra();
    List<Obra> buscarPorPalavraChave(String palavraChave);
    List<Obra> buscarPorValidado(boolean validado);
    List<Obra> buscarObraPorTitulo(String titulo);
    List<Obra> buscarObraPorAutor(String nomeAutor);
    List<Obra> buscarPorDataPublicacao(LocalDate dataInicio, LocalDate dataFim);
}
