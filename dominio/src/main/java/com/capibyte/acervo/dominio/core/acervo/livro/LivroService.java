package com.capibyte.acervo.dominio.core.acervo.livro;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void salvar(Livro livro){
        livroRepository.salvar(livro);
    }

    public void deletar(Isbn isbn){
        livroRepository.deletar(isbn);
    }

    public Livro buscarPorIsbn(Isbn isbn){
        return livroRepository.buscarPorIsbn(isbn);
    }

    public List<Livro> listarTodos(){
        return livroRepository.obterTodos();
    }

    public List<Livro> buscarPorTema(String tema){
        return livroRepository.buscarPorTema(tema);
    }
}
