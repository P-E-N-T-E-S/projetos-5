package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ListaDTO;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.salvo.LeituraService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.infraestrutura.security.userdetail.UsuarioDetalhes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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

    @PostMapping("/{id}/adicionarLivro")
    public ResponseEntity<String> adicionarLivro(@PathVariable Long id, String isbn) {
        
    }
}
