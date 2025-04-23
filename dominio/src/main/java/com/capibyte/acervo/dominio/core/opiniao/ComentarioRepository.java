package com.capibyte.acervo.dominio.core.opiniao;

import java.util.List;

public interface ComentarioRepository {
    void salvar(Comentario comentario);
    List<Comentario> listarPorIsbn(String isbn);
}
