package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro;

import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

@Repository
public class LivroImpl implements LivroRepository {

    private LivroRepositorio repositorio;

    private JpaMapeador mapeador;

    public LivroImpl(LivroRepositorio repositorio, JpaMapeador mapeador) {
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public void salvar(Livro livro){
        LivroJPA livroJPA = mapeador.map(livro, LivroJPA.class);
        repositorio.save(livroJPA);
    }
}
