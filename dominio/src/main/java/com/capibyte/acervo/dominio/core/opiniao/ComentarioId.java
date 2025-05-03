package com.capibyte.acervo.dominio.core.opiniao;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.util.Assert.isTrue;

public class ComentarioId {
    private int id;

    public ComentarioId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}