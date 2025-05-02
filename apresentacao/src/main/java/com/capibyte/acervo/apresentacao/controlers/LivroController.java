package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ComentarioDTO;
import com.capibyte.acervo.apresentacao.dto.LivroDTO;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorService;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroService;
import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private ComentarioService comentarioService;

    private LivroService livroService;

    private AutorService autorService;

    public LivroController(ComentarioService comentarioService, LivroService livroService, AutorService autorService) {
        this.comentarioService = comentarioService;
        this.livroService = livroService;
        this.autorService = autorService;
    }

    @PostMapping("/lib/adicionar")
    public ResponseEntity<String> adicionarLivro(@RequestBody LivroDTO livroDTO) {
        List<AutorId> autores = autorService.processarEntrada(livroDTO.autores());
        livroService.salvar(new Livro(new Isbn(livroDTO.isbn()), livroDTO.titulo(), autores, livroDTO.sinopse(), livroDTO.numeroChamada(), livroDTO.anoPublicacao(), livroDTO.quantidadeDePaginas(), livroDTO.temas()));
        return ResponseEntity.ok("Livro adicionado com sucesso");
    }

    @GetMapping("/comentarios/{isbn}")
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