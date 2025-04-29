package com.capibyte.acervo.dominio.core.administracao.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salvar(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    public Usuario buscarPorMatricula(String matricula) {
        return usuarioRepository.buscarPorMatricula(matricula);
    }
}
