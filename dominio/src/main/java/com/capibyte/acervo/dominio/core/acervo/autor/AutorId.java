package com.capibyte.acervo.dominio.core.acervo.autor;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notNull;

public class AutorId {
    Integer id;

    public AutorId(Integer id) {
        notNull(id, "o id não pode ser nulo");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AutorId{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AutorId) {
            var instance = (AutorId) o;
            return Objects.equals(id, instance.getId());
        }
        return false;
    }
}
