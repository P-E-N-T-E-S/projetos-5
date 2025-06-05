package com.capibyte.acervo.dominio.core.acervo.livro;

import com.capibyte.acervo.dominio.log.PesquisaLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository livroRepository;
    private PesquisaLogService pesquisaLogService;

    public LivroService(LivroRepository livroRepository, PesquisaLogService pesquisaLogService) {
        this.livroRepository = livroRepository;
        this.pesquisaLogService = pesquisaLogService;
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
        pesquisaLogService.salvar(tema);
        return livroRepository.buscarPorTema(tema);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.buscarPorTitulo(titulo);
    }

    public List<Livro> buscarPorAutor(String nomeAutor) {
        return livroRepository.buscarPorAutor(nomeAutor);
    }

    public List<Livro> buscarPorAnoPublicacao(int anoInicio, int anoFim) {
        return livroRepository.buscarPorAnoPublicacao(anoInicio, anoFim);
    }

    public List<Livro> buscarPorNumeroChamada(String numeroChamada) {
        return livroRepository.buscarPorNumeroChamada(numeroChamada);
    }
}
