package com.capibyte.acervo.infraestrutura.persistencia.core.log;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesquisaLogRepositorio extends JpaRepository<PesquisaLog, Long> {
}
