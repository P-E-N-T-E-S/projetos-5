package com.capibyte.acervo.apresentacao.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemplares")
public class ExemplarController {

    @PostMapping("/adicionar")
    public ResponseEntity<String > adicionarExemplar(@RequestBody String isbn) {

    }
}
