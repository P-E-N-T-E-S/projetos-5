package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario;

import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.UsuarioRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositorioImpl implements UsuarioRepository {

    private UsuarioRepositorio usuarioRepository;
    private JpaMapeador mapeador;

    public UsuarioRepositorioImpl(UsuarioRepositorio usuarioRepository, JpaMapeador mapeador) {
        this.usuarioRepository = usuarioRepository;
        this.mapeador = mapeador;
    }

    public void salvar(Usuario usuario){
        usuarioRepository.save(mapeador.map(usuario, UsuarioJPA.class));
    }

    public Usuario buscarPorMatricula(String matricula){
        return mapeador.map(usuarioRepository.findByMatricula(matricula), Usuario.class);
    }
}
