package com.capibyte.acervo.infraestrutura.seguranca;

import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.gateway.UsuarioGateway;
import com.capibyte.acervo.infraestrutura.security.userdetail.UsuarioDetalhes;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioGateway usuarioGateway;

    public UsuarioDetailsService(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Usuario usuario = usuarioGateway.buscarPorMatricula(matricula)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com a matrícula: " + matricula));
        return new UsuarioDetalhes(usuario);
    }
}
