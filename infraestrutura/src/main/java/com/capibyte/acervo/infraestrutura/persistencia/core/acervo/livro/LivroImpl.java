package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public void deletar(Isbn isbn) {
        repositorio.deleteById(isbn.toString());
    }

    @Override
    public Livro buscarPorIsbn(Isbn isbn) {
        return repositorio.findById(isbn.toString()).map(jpa -> mapeador.map(jpa, Livro.class)).orElse(null) ;
    }

    @Override
    public List<Livro> obterTodos() {
        return repositorio.findAll().stream().map(jpa -> mapeador.map(jpa, Livro.class)).toList() ;
    }

    @Override
    public List<Livro> buscarPorTema(String tema) {
        return repositorio.findByTema(tema).stream().map(jpa -> mapeador.map(jpa, Livro.class)).toList() ;
    }
}
