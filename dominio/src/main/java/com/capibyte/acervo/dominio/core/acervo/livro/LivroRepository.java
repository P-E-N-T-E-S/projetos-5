package com.capibyte.acervo.dominio.core.acervo.livro;

public interface LivroRepository {
    void salvar(Livro livro);
    void deletar(Isbn isbn);
}
