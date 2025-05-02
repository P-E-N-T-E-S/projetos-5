package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @GetMapping("/livro/isbn")
    public ResponseEntity<List<Autor>> listarAutoresPorIsbn() {

    }
}
