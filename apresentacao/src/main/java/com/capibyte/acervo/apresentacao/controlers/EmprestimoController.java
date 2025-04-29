package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.SolicitacaoDTO;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.EmprestimoService;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/aprovar")
    public ResponseEntity<String> reservar(@RequestBody SolicitacaoDTO solicitacaoDTO) {
        emprestimoService.ValidarSolicitacao(new Solicitacao(new Matricula(solicitacaoDTO.tomador()), LocalDate.now(), solicitacaoDTO.exemplares().stream().map(exemplar -> new ExemplarId(exemplar)).toList()));
        return ResponseEntity.ok("Livro reservado com sucesso");
    }
    
}
