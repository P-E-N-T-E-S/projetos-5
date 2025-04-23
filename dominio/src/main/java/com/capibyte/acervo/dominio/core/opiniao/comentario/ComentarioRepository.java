package com.capibyte.acervo.dominio.core.opiniao.comentario;

import java.util.List;

public interface ComentarioRepository {
    void salvar(Comentario comentario);
    List<Comentario> listarPorIsbn(String isbn);
}
