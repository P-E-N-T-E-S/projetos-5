package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.salvo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaRepositorio extends JpaRepository<ListaLeituraJPA, Long> {
    List<ListaLeituraJPA> findManyByUsuarioMatricula(String matricula);
}
