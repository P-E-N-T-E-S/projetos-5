package com.capibyte.acervo.dominio.core.administracao.usuario;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Matricula {
    String codigo;

    public Matricula(String codigo) {
        notNull(codigo, "O codigo não pode ser nulo");
        notBlank(codigo, "O codigo não pode estar vazio");
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }
}
