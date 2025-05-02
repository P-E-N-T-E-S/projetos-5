package com.capibyte.acervo.dominio.core.acervo.livro;

import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void salvar(Livro livro){
        livroRepository.salvar(livro);
    }
}
