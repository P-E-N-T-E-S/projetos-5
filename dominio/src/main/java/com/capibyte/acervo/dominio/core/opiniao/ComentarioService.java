package com.capibyte.acervo.dominio.core.opiniao;

import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public void adicionarComentario(Comentario comentario){
        comentarioRepository.salvar(comentario);
    }

    public List<Comentario> listarComentarios(String isbnLivro) {
        return comentarioRepository.listarPorIsbn(isbnLivro);
    }
}
