package com.capibyte.acervo.infraestrutura.security.auth;

public class AcessDTO {
    private String token;
    private String cargo;

    public AcessDTO(String token, String cargo) {
        this.token = token;
        this.cargo = cargo;
    }

    public String getToken() {
        return token;
    }

    public String getCargo() {
        return cargo;
    }
}
