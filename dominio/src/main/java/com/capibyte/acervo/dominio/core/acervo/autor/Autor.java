package com.capibyte.acervo.dominio.core.acervo.autor;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Autor {

    AutorId id;
    String nome;

    public Autor(AutorId id, String nome) {
        notNull(id, "O id não pode ser nulo");

        this.id = id;
        setNome(nome);
    }

    public void setNome(String nome) {
        notNull(nome, "O nome do autor não pode ser nulo");
        notBlank("O nome do autor não pode estar em branco");
        this.nome = nome;
    }

    public AutorId getId() {
        return id;
    }

    @Override
    public String toString() {
        return nome;
    }
}
