package com.capibyte.acervo.dominio.core.administracao.usuario;

import java.util.Optional;

public interface UsuarioGateway {
    Optional<Usuario> buscarPorMatricula(String matricula);
}
