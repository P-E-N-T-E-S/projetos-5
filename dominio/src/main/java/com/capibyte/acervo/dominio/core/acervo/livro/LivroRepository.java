package com.capibyte.acervo.dominio.core.acervo.livro;

import java.util.List;

public interface LivroRepository {
    void salvar(Livro livro);
    void deletar(Isbn isbn);
    Livro buscarPorIsbn(Isbn isbn);
    List<Livro> obterTodos();
    List<Livro> buscarPorTema(String tema);
    List<Livro> buscarPorTitulo(String titulo);
    List<Livro> buscarPorAutor(String nomeAutor);
    List<Livro> buscarPorAnoPublicacao(int anoInicio, int anoFim);
    List<Livro> buscarPorNumeroChamada(String numeroChamada);
}
