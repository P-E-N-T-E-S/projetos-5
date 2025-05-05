package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObraRepositorio extends JpaRepository<ObraJPA, String> {

    List<ObraJPA> findByPalavrasChave(String palavraChave);
}
