package com.capibyte.acervo.dominio.core.acervo.exemplar;

public class Localizacao {
    private String andar;
    private String prateleira;

    public Localizacao(String andar, String prateleira) {
        this.andar = andar;
        this.prateleira = prateleira;
    }

    public String getAndar() {
        return andar;
    }

    public String getPrateleira() {
        return prateleira;
    }
}
