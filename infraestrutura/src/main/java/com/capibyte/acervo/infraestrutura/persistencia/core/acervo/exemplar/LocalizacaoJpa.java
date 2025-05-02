package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar;

import jakarta.persistence.Embeddable;

@Embeddable
public class LocalizacaoJpa {
    String andar;
    String prateleira;

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }
}
