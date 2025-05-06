package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ListaDTO;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.administracao.salvo.LeituraService;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaId;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaLeitura;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.infraestrutura.security.userdetail.UsuarioDetalhes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista")
public class ListaController {

    private LeituraService leituraService;

    public ListaController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarLista(ListaDTO listaDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UsuarioDetalhes usuarioDetalhes) {
            Usuario usuario =  usuarioDetalhes.getUsuario();
            if (listaDTO.livro().isBlank() && listaDTO.obra().isBlank() ){
                leituraService.criarLista(usuario.getMatricula() ,listaDTO.titulo(), listaDTO.descricao(), null, null, listaDTO.privado());
            } else if (listaDTO.livro().isBlank()) {
                leituraService.criarLista(usuario.getMatricula() ,listaDTO.titulo(), listaDTO.descricao(), null, new DOI(listaDTO.obra()), listaDTO.privado());
            }else if (listaDTO.obra().isBlank()) {
                leituraService.criarLista(usuario.getMatricula() ,listaDTO.titulo(), listaDTO.descricao(), new Isbn(listaDTO.livro()), null, listaDTO.privado());
            }else {
                leituraService.criarLista(usuario.getMatricula() ,listaDTO.titulo(), listaDTO.descricao(), new Isbn(listaDTO.livro()), new DOI(listaDTO.obra()), listaDTO.privado());
            }
            return new ResponseEntity<>("Lista criada!", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Usuário não autenticado", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}/adicionarLivro")
    public ResponseEntity<String> adicionarLivro(@PathVariable Long id, String isbn) {
        leituraService.adicionarLivro(new ListaId(id), new Isbn(isbn));
        return new ResponseEntity<>("Livro adicionado à lista!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}/adicionarObra")
    public ResponseEntity<String> adicionarObra(@PathVariable Long id, String doi) {
        leituraService.adicionarObra(new ListaId(id), new DOI(doi));
        return new ResponseEntity<>("Obra adicionada à lista!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}/removerObra")
    public ResponseEntity<String> removerObra(@PathVariable Long id, String codigoDaObra) {
        leituraService.removerObra(new ListaId(id), new DOI(codigoDaObra));
        return new ResponseEntity<>("Obra removido da lista!", HttpStatus.OK);
    }

    @PutMapping("/{id}/removerLivro")
    public ResponseEntity<String> removerLivro(@PathVariable Long id, String isbn) {
        leituraService.removerLivro(new ListaId(id), new Isbn(isbn));
        return new ResponseEntity<>("Livro removido da lista!", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ListaLeitura>> listarLeituras(@RequestParam String matricula){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UsuarioDetalhes usuarioDetalhes) {
            Usuario tomador =  usuarioDetalhes.getUsuario();
            if (tomador.getMatricula().toString().equals(matricula)) {
                return ResponseEntity.ok(leituraService.listarTodasLeiturasPorAluno(tomador.getMatricula()));
            }
            return ResponseEntity.ok(leituraService.listarLeiturasPublicasPorAluno(new Matricula(matricula)) );
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaLeitura> listarLeitura(@PathVariable Long id){
        return ResponseEntity.ok(leituraService.buscarListaPorID(new ListaId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLista(@PathVariable Long id){
        leituraService.excluirLista(new ListaId(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
