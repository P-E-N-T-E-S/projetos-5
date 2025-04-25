package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepositorioJPA extends JpaRepository<SolicitacaoJPA, Long> {
    SolicitacaoJPA findByMatricula(String matricula);
}