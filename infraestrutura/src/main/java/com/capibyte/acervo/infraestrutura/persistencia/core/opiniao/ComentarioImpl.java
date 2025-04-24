package com.capibyte.acervo.infraestrutura.persistencia.core.opiniao;

import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ComentarioImpl implements ComentarioRepository {
    private final ComentarioRepositoryJPA comentarioRepositoryJPA;
    private final JpaMapeador mapeador;

    public ComentarioImpl (ComentarioRepositoryJPA comentarioRepositoryJPA, JpaMapeador mapeador) {
        this.comentarioRepositoryJPA = comentarioRepositoryJPA;
        this.mapeador = mapeador;
    }

    @Override
    public void salvar(Comentario comentario) {
        ComentarioJPA entidade = mapeador.map(comentario, ComentarioJPA.class);
        comentarioRepositoryJPA.save(entidade);
    }

    @Override
    public List<Comentario> listarPorIsbn(String isbn) {
        return comentarioRepositoryJPA.findByLivro_Isbn(isbn)
                .stream()
                .map(jpa -> mapeador.map(jpa, Comentario.class))
                .collect(Collectors.toList());
    }
}
