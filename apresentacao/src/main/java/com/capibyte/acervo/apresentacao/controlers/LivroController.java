package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ComentarioDTO;
import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private ComentarioService comentarioService;

    @GetMapping("/{isbn}")
    public List<ComentarioDTO> paginaLivro(@PathVariable String isbn) {
        List<Comentario> comentarios = comentarioService.listarComentarios(isbn);

        return comentarios.stream()
                .map(comentario -> new ComentarioDTO(
                        comentario.getId(),
                        comentario.getIsbn().getIsbn().toString(),
                        comentario.getConteudo(),
                        comentario.getUsuario().getNome(),
                        comentario.getUsuario().getCargo().name(),
                        comentario.getDataCriacao()
                ))
                .collect(Collectors.toList());
    }
}