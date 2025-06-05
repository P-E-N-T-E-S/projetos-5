package com.capibyte.acervo.apresentacao.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capibyte.acervo.infraestrutura.persistencia.DatabasePopulator;

@RestController
@RequestMapping("/api/admin")
public class DatabasePopulatorController {

    @Autowired
    private DatabasePopulator databasePopulator;

    @PostMapping("/popular-banco")
    public ResponseEntity<String> popularBancoDeDados() {
        try {
            databasePopulator.popularBancoDeDados();
            return ResponseEntity.ok("Banco de dados populado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao popular banco de dados: " + e.getMessage());
        }
    }
} 