package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.dominio.core.administracao.emprestimo.EmprestimoService;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    private SolicitacaoService solicitacaoService;

    public EmprestimoController(EmprestimoService emprestimoService, SolicitacaoService solicitacaoService) {
        this.emprestimoService = emprestimoService;
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<String> aprovar(@PathVariable Long id) {
        emprestimoService.aprovarEmprestimo(id);
        return ResponseEntity.ok("Livro reservado com sucesso");
    }

    @DeleteMapping("/recusar/{id}")
    public ResponseEntity<String> recusar(@PathVariable Long id) {
        emprestimoService.recusarEmprestimo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/solicitacoes")
    public ResponseEntity<List<Solicitacao>> listar(){
        return ResponseEntity.ok(solicitacaoService.listarSolicitacoes());
    }
}
