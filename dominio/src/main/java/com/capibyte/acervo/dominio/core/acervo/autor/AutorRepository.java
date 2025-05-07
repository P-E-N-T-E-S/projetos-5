package com.capibyte.acervo.dominio.core.acervo.autor;

public interface AutorRepository {

    void salvar(Autor autor);
    Autor buscarPorNome(String nome);
    Autor buscarPorId(AutorId id);
}
