package com.capibyte.acervo.dominio.core.administracao.usuario;

public interface UsuarioRepository {

    void salvar(Usuario usuario);

    Usuario buscarPorMatricula(String matricula);
}
