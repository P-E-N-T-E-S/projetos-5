package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJPA, String> {
    UsuarioJPA findByMatricula(String matricula);
}
