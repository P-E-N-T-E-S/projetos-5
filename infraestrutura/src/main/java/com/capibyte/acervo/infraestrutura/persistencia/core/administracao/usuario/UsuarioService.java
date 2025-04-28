package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioJPA buscarPorMatricula(String matricula){
        return usuarioRepository.findByMatricula(matricula);
    }
}
