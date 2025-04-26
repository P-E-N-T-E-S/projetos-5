package com.capibyte.acervo.infraestrutura.security.exceptions;

public class UsuarioJaExistente extends RuntimeException {
    public UsuarioJaExistente(String message) {
        super(message);
    }
}
