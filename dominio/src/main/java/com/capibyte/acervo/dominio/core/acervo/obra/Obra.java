package com.capibyte.acervo.dominio.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;

import java.time.LocalDate;
import java.util.List;

public class Obra { //obra digital, tcc
    private String titulo;
    private List<Autor> autores;
    private List<String> palavrasChave;
    private String DOI;
    private String resumo;
    private LocalDate dataPublicacao;
    private String citacaoAbnt;
}
