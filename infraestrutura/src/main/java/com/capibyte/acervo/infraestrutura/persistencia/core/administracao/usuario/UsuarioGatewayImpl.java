package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario;

import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.UsuarioGateway;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioJpaRepository repository;
    private final JpaMapeador mapeador;

    public UsuarioGatewayImpl(UsuarioJpaRepository repository, JpaMapeador mapeador) {
        this.repository = repository;
        this.mapeador = mapeador;
    }

    @Override
    public Optional<Usuario> buscarPorMatricula(String matricula) {
        UsuarioJPA usuarioJPA = repository.findByMatricula(matricula);
        if (usuarioJPA == null) {
            return Optional.empty();
        }
        Usuario usuario = mapeador.map(usuarioJPA, Usuario.class);
        return Optional.of(usuario);
    }
}
