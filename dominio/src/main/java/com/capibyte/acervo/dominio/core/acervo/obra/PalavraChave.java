package com.capibyte.acervo.dominio.core.acervo.obra;

public class PalavraChave {
    private final String palavraChave;

    public PalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    @Override
    public String toString() {
        return palavraChave;
    }
}
