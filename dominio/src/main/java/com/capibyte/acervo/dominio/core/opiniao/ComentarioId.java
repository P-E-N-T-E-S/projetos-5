package com.capibyte.acervo.dominio.core.opiniao;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.util.Assert.isTrue;

public class ComentarioId {
    private static final AtomicInteger sequencia = new AtomicInteger(0);
    private final int id;

    public ComentarioId(int id) {
        isTrue(id > 0, "O id deve ser positivo");
        this.id = id;
    }

    public static ComentarioId gerar() {
        return new ComentarioId(sequencia.incrementAndGet());
    }

    public static ComentarioId fromInt(int id) {
        return new ComentarioId(id);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ComentarioId that = (ComentarioId) obj;
        return id == that.id;
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