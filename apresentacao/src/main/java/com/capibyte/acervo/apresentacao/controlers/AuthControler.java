package com.capibyte.acervo.apresentacao.controlers;

import com.capibyte.acervo.apresentacao.dto.AuthDTO;
import com.capibyte.acervo.dominio.core.administracao.usuario.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthControler {

    private AuthService authService;

    public AuthControler(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody AuthDTO authDto) {
        try {
            return ResponseEntity.ok(authService.login(authDto));
        }catch (BadCredentialsException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos");
            }

        }

}
