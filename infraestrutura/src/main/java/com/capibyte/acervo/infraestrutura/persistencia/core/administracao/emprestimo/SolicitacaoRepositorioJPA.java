package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolicitacaoRepositorioJPA extends JpaRepository<SolicitacaoJPA, Long> {
    SolicitacaoJPA findByMatricula(String matricula);
    Optional<SolicitacaoJPA> findById(Long id);
}