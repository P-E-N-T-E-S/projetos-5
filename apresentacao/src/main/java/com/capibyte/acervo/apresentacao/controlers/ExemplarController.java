package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.EmprestimoDTO;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.EmprestimoService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.infraestrutura.security.userdetail.UsuarioDetalhes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemplar")
public class ExemplarController {

    private ExemplarService exemplarService;
    private EmprestimoService emprestimoService;

    public ExemplarController(ExemplarService exemplarService, EmprestimoService emprestimoService) {
        this.exemplarService = exemplarService;
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/reservar")
    public ResponseEntity<String> reservar(@RequestBody EmprestimoDTO emprestimoDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cargo cargo = null;

        if (auth != null && auth.getPrincipal() instanceof UsuarioDetalhes usuarioDetalhes) {
            Usuario usuario = usuarioDetalhes.getUsuario();
            cargo = usuario.getCargo();
        } else {
            throw new IllegalStateException("Usuário não autenticado.");
        }
        emprestimoService.realizarEmprestimo(new ExemplarId(emprestimoDTO.getExemplarId()), new Matricula(emprestimoDTO.getMatricula()), cargo);
        return ResponseEntity.ok("Livro reservado com sucesso");
    }
}
