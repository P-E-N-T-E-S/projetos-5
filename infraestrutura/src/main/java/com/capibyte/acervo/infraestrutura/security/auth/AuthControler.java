package com.capibyte.acervo.infraestrutura.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(authService.login(authDto));
    }

}
