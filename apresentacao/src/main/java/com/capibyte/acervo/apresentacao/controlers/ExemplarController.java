package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ExemplarDTO;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Localizacao;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exemplares")
public class ExemplarController {

    private ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String > adicionarExemplar(@RequestBody ExemplarDTO exemplarDTO) {
        exemplarService.salvar(new Exemplar(new CodigoDaObra(exemplarDTO.codigo()), new Isbn(exemplarDTO.isbn()), new Localizacao(exemplarDTO.andar(), exemplarDTO.prateleira())));
        return ResponseEntity.ok("Exemplar adicionado com sucesso");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarExemplar(@RequestParam Long codigo) {
        exemplarService.deletar(new CodigoDaObra(codigo));
        return ResponseEntity.ok("Exemplar deletado com sucesso");
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Exemplar>obterPorCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(exemplarService.buscarPorId(new CodigoDaObra(codigo)));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<String> definirStatus(@PathVariable Long codigo, @RequestParam("status") int status) {
        exemplarService.definirStatus(new CodigoDaObra(codigo), status);
        return new ResponseEntity<>("Exemplar definado com sucesso",  HttpStatus.NO_CONTENT);
    }
}
