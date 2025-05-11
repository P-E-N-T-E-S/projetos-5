package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.ObraDTO;
import com.capibyte.acervo.apresentacao.dto.ObraDetalhadaDTO;
import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorService;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.DoiUtils;
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

    private final ObraService obraService;
    private final AutorService autorService;

    public ObraController(ObraService obraService, AutorService autorService) {
        this.obraService = obraService;
        this.autorService = autorService;
    }

    @PostMapping(value = "/lib/adicionar")
    public ResponseEntity<String> adicionarObra(@RequestBody ObraDTO obraDTO) {
        List<AutorId> autores = autorService.processarEntrada(obraDTO.autores());
        List<PalavraChave> palavraChaves = obraDTO.palavrasChave().stream().map(PalavraChave::new).toList();
        obraService.salvar(new Obra(
            new DOI(obraDTO.doi()),
            obraDTO.titulo(),
            autores,
            palavraChaves,
            obraDTO.resumo(),
            obraDTO.dataPublicacao(),
            obraDTO.citacaoAbnt(),
            new byte[0],
                false
        ));
        return ResponseEntity.ok("Obra adicionada com sucesso");
    }

    @PostMapping(value = "/lib/{doi}/upload")
    public ResponseEntity<String> uploadPdf(@PathVariable String doi, @RequestPart MultipartFile file) throws IOException {
        DOI doiAjustado = new DOI(DoiUtils.mascarar(doi));
        obraService.salvarPdf(doiAjustado, file.getBytes());
        return ResponseEntity.ok("Arquivo PDF salvo com sucesso");
    }

    @DeleteMapping("/lib/deletar")
    public ResponseEntity<String> deletarObra(@RequestParam String doi) {
        obraService.deletar(new DOI(doi));
        return ResponseEntity.ok("Obra deletada com sucesso");
    }

    @GetMapping("/{doi}")
    public ResponseEntity<ObraDetalhadaDTO>obterPorDoi(@PathVariable String doi) {
        DOI doiAjustado = new DOI(DoiUtils.mascarar(doi));
        Obra obra = obraService.buscarPorId(doiAjustado);
        return ResponseEntity.ok(this.toDTO(obra));
    }

    @GetMapping("/{doi}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String doi) {

        DOI doiAjustado = new DOI(DoiUtils.mascarar(doi));

        byte[] pdf = obraService.buscarPDF(doiAjustado);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("obra_" + doi + ".pdf").build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<ObraDetalhadaDTO>>listarObras() {
        List<Obra> obras = obraService.listarTodos();
        return ResponseEntity.ok(obras.stream().map(this::toDTO).toList());
    }

    @GetMapping("/validado")
    public ResponseEntity<List<ObraDetalhadaDTO>>listarObrasValidadas(@RequestParam(required = false) Boolean validado) {
        List<Obra> obras = obraService.buscarPorValidado(validado);
        return ResponseEntity.ok(obras.stream().map(this::toDTO).toList());
    }

    @PutMapping("/lib/{doi}/validar")
    public ResponseEntity<String> validarObra(@PathVariable String doi) {
        DOI doiAjustado = new DOI(DoiUtils.mascarar(doi));
        obraService.validar(doiAjustado);
        return ResponseEntity.ok("Obra validada com sucesso");
    }

    @GetMapping("/chave")
    public ResponseEntity<List<ObraDetalhadaDTO>>obterObrasPorPalavraChave(@RequestParam String palavraChave) {
        List<Obra> obras = obraService.buscarPorPalavraChave(palavraChave);
        return ResponseEntity.ok(obras.stream().map(this::toDTO).toList());
    }

    public ObraDetalhadaDTO toDTO(Obra obra) {
        List<ObraDetalhadaDTO.AutorDTO> autoresDTO = obra.getAutores().stream()
                .map(autorId -> {
                    Autor autor = autorService.buscarPorId(autorId);
                    return new ObraDetalhadaDTO.AutorDTO(autorId.getId(), autor.getNome());
                })
                .toList();

        List<String> palavras = obra.getPalavrasChave().stream()
                .map(PalavraChave::toString)
                .toList();

        return new ObraDetalhadaDTO(
                obra.getDoi().getCodigo(),
                obra.getTitulo(),
                autoresDTO,
                palavras,
                obra.getResumo(),
                obra.getDataPublicacao(),
                obra.getCitacaoAbnt()
        );
    }

}