package com.capibyte.acervo.dominio.core.acervo.exemplar;

import static org.apache.commons.lang3.Validate.notNull;

public class CodigoDaObra {
    private Long id;

    public CodigoDaObra(Long id) {
        notNull(id, "O número do id não pode ser nulo");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
