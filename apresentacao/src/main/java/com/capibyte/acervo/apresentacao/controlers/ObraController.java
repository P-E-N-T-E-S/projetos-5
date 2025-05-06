package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ObraDTO;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorService;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.ObraService;
import com.capibyte.acervo.dominio.core.acervo.obra.PalavraChave;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<String> adicionarObra(@RequestBody ObraDTO obraDTO, @RequestParam("file") MultipartFile file) {
        List<AutorId> autores = autorService.processarEntrada(obraDTO.autores());
        List<PalavraChave> palavraChaves = obraDTO.palavrasChave().stream().map(PalavraChave::new).toList();
        try {
            obraService.salvar(new Obra(new DOI(obraDTO.doi()), obraDTO.titulo(), autores, palavraChaves, obraDTO.resumo(), obraDTO.dataPublicacao(), obraDTO.citacaoAbnt(), file.getBytes()));
            return ResponseEntity.ok("Obra adicionada com sucesso");
        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }

    @DeleteMapping("/lib/deletar")
    public ResponseEntity<String> deletarObra(@RequestParam String doi) {
        obraService.deletar(new DOI(doi));
        return ResponseEntity.ok("Obra deletada com sucesso");
    }

    @GetMapping("/{doi}")
    public ResponseEntity<Obra>obterPorDoi(@PathVariable String doi) {
        Obra obra = obraService.buscarPorId(new DOI(doi));
        obra.setArquivoPdf(null);
        return ResponseEntity.ok(obra);
    }

    @GetMapping("/{doi}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String doi) {

        byte[] pdf = obraService.buscarPDF(new DOI(doi));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("obra_" + doi + ".pdf").build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<Obra>>listarObras() {
        return ResponseEntity.ok(obraService.listarTodos());
    }

    @GetMapping("/chave/{palavraChave}")
    public ResponseEntity<List<Obra>>obterObrasPorPalavraChave(@RequestParam String palavraChave) {
        return ResponseEntity.ok(obraService.buscarPorPalavraChave(palavraChave));
    }
}
