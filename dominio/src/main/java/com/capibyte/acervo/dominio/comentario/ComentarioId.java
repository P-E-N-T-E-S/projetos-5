package com.capibyte.acervo.dominio.comentario;

import java.util.Objects;
import static org.springframework.util.Assert.isTrue;

public class ComentarioId {
    private final int id;

    public ComentarioId(int id) {
        isTrue(id > 0, "O id deve ser positivo");

        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ComentarioId) {
            var comentarioId = (ComentarioId) obj;
            return id == comentarioId.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
