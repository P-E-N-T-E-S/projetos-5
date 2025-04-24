package com.capibyte.acervo.dominio.core.opiniao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository {
    void salvar(Comentario comentario);
    List<Comentario> listarPorIsbn(String isbn);
}
