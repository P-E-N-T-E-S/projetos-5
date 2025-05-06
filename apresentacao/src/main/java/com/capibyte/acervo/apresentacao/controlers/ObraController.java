package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ObraDTO;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorService;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.ObraService;
import com.capibyte.acervo.dominio.core.acervo.obra.PalavraChave;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private ObraService obraService;

    private AutorService autorService;

    public ObraController(ObraService obraService, AutorService autorService) {
        this.obraService = obraService;
        this.autorService = autorService;
    }

    @PostMapping("/lib/adicionar")
    public ResponseEntity<String> adicionarObra(ObraDTO obraDTO) {
        List<AutorId> autores = autorService.processarEntrada(obraDTO.autores());
        List<PalavraChave> palavraChaves = obraDTO.palavrasChave().stream().map(PalavraChave::new).toList();
        obraService.salvar(new Obra(new DOI(obraDTO.doi()), obraDTO.titulo(), autores, palavraChaves, obraDTO.resumo(), obraDTO.dataPublicacao(), obraDTO.citacaoAbnt()));
        return ResponseEntity.ok("Obra adicionada com sucesso");
    }

    @DeleteMapping("/lib/deletar")
    public ResponseEntity<String> deletarObra(@RequestParam String doi) {
        obraService.deletar(new DOI(doi));
        return ResponseEntity.ok("Obra deletada com sucesso");
    }

    @GetMapping("/{doi}")
    public ResponseEntity<Obra>obterPorDoi(@PathVariable String doi) {
        return ResponseEntity.ok(obraService.buscarPorId(new DOI(doi)));
    }
}
