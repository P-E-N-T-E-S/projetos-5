package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.LivroDTO;
import com.capibyte.acervo.apresentacao.dto.LivroDetalhadoDTO;
import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
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
    public ResponseEntity<LivroDetalhadoDTO>obterPorIsbn(@PathVariable String isbn) {
        Livro livro = livroService.buscarPorIsbn(new Isbn(isbn));
        return ResponseEntity.ok(this.toDTO(livro));
    }

    @GetMapping()
    public ResponseEntity<List<LivroDetalhadoDTO>>listarLivros() {
        List<Livro> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros.stream().map(this::toDTO).toList());
    }

    @GetMapping("/tema/{tema}")
    public ResponseEntity<List<LivroDetalhadoDTO>>listarLivrosPorTemas(@PathVariable String tema) {
        List<Livro> livros = livroService.buscarPorTema(tema);
        return ResponseEntity.ok(livros.stream().map(this::toDTO).toList());
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

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroDetalhadoDTO>> buscarPorTitulo(@RequestParam String titulo) {
        List<Livro> livros = livroService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(livros.stream().map(this::toDTO).toList());
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LivroDetalhadoDTO>> buscarPorAutor(@RequestParam String nomeAutor) {
        List<Livro> livros = livroService.buscarPorAutor(nomeAutor);
        return ResponseEntity.ok(livros.stream().map(this::toDTO).toList());
    }

    @GetMapping("/ano")
    public ResponseEntity<List<LivroDetalhadoDTO>> buscarPorAnoPublicacao(
            @RequestParam int anoInicio,
            @RequestParam int anoFim) {
        List<Livro> livros = livroService.buscarPorAnoPublicacao(anoInicio, anoFim);
        return ResponseEntity.ok(livros.stream().map(this::toDTO).toList());
    }

    @GetMapping("/chamada")
    public ResponseEntity<List<LivroDetalhadoDTO>> buscarPorNumeroChamada(@RequestParam String numeroChamada) {
        List<Livro> livros = livroService.buscarPorNumeroChamada(numeroChamada);
        return ResponseEntity.ok(livros.stream().map(this::toDTO).toList());
    }

    public LivroDetalhadoDTO toDTO(Livro livro) {
        List<LivroDetalhadoDTO.AutorDTO> autoresDTO = livro.getAutores().stream()
                .map(autorId -> {
                    Autor autor = autorService.buscarPorId(autorId);
                    return new LivroDetalhadoDTO.AutorDTO(autorId.getId(), autor.getNome());
                })
                .toList();

        return new LivroDetalhadoDTO(
                livro.getIsbn().toString(),
                livro.getTitulo(),
                autoresDTO,
                livro.getSinpose(),
                livro.getNumeroChamada(),
                livro.getAnoDePublicacao(),
                livro.getQuantidadeDePaginas(),
                livro.getTemas()
        );
    }

}