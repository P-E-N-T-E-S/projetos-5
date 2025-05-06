package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.LivroDTO;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorService;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioService;
import com.capibyte.acervo.infraestrutura.security.userdetail.UsuarioDetalhes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @DeleteMapping("/lib/deletar")
    public ResponseEntity<String> deletarLivro(@RequestParam String isbn) {
        livroService.deletar(new Isbn(isbn));
        return ResponseEntity.ok("Livro deletado com sucesso");
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Livro>obterPorIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(livroService.buscarPorIsbn(new Isbn(isbn)));
    }

    @GetMapping()
    public ResponseEntity<List<Livro>>listarLivros() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping()
    public ResponseEntity<List<Livro>>listarLivrosPorTemas(@RequestParam String tema) {
        return ResponseEntity.ok(livroService.buscarPorTema(tema));
    }

    @GetMapping("/{isbn}/comentarios")
    public ResponseEntity<List<Comentario>>paginaLivro(@PathVariable String isbn) {
        return ResponseEntity.ok(comentarioService.listarComentarios(isbn));
    }

    @PostMapping("/{isbn}/comentar")
    public ResponseEntity<String> adicionarComentario(@RequestBody String conteudo, @PathVariable String isbn) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UsuarioDetalhes usuarioDetalhes) {
            Usuario tomador = usuarioDetalhes.getUsuario();
            comentarioService.adicionarComentario(new Comentario(new Isbn(isbn), conteudo, LocalDateTime.now(), tomador.getMatricula()));
            return new ResponseEntity<>("Comentário adicionado com sucesso", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Usuário não autenticado", HttpStatus.UNAUTHORIZED);
        }
    }
}