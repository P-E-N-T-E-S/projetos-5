package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ExemplarDTO;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Localizacao;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
