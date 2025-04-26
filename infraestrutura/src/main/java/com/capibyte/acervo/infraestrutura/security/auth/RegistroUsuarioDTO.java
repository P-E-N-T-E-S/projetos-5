package com.capibyte.acervo.infraestrutura.security.auth;

public record RegistroUsuarioDTO(String matricula, String nome, String senha, String email, int cargo) {
}
