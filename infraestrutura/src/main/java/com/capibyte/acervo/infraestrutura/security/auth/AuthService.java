package com.capibyte.acervo.infraestrutura.security.auth;

import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJpaRepository;
import com.capibyte.acervo.infraestrutura.security.exceptions.UsuarioJaExistente;
import com.capibyte.acervo.infraestrutura.security.jwt.JwtUtils;
import com.capibyte.acervo.infraestrutura.security.userdetail.UsuarioDetalhes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    private UsuarioJpaRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UsuarioJpaRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AcessDTO login(AuthDTO authDTO){

        try {
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UsuarioDetalhes userDetails = (UsuarioDetalhes) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetails(userDetails);

            String cargo = userDetails.getAuthorities().iterator().next().getAuthority();

            return new AcessDTO(token, cargo);
        }catch (BadCredentialsException e){
            //TODO: Login ou senha invalidos
            System.out.println(e.getMessage());
        }
        return null;
    }

    public AcessDTO registrar(RegistroUsuarioDTO registroUsuarioDTO) throws UsuarioJaExistente {
        if(usuarioRepository.existsById(registroUsuarioDTO.matricula())){
            throw new UsuarioJaExistente(registroUsuarioDTO.matricula());
        }
        UsuarioJPA usuario = new UsuarioJPA();
        usuario.setMatricula(registroUsuarioDTO.matricula());
        usuario.setNome(registroUsuarioDTO.nome());
        usuario.setSenha(passwordEncoder.encode(registroUsuarioDTO.senha()));
        usuario.setCargo(registroUsuarioDTO.cargo());
        usuario.setEmail(registroUsuarioDTO.email());
        usuarioRepository.save(usuario);

        return login(new AuthDTO(registroUsuarioDTO.matricula(), registroUsuarioDTO.senha()));
    }
}
