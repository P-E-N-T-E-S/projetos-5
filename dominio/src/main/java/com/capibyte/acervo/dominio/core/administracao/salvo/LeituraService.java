package com.capibyte.acervo.dominio.core.administracao.salvo;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraService {

    private LeituraRepository repository;

    public LeituraService(LeituraRepository repository) {
        this.repository = repository;
    }

    public void criarLista(Matricula aluno, String titulo, String descricao, Isbn livro, DOI obra, boolean privado){
        ListaLeitura lista = new ListaLeitura(aluno, titulo, descricao, privado);
        if(livro != null) lista.addLivro(livro);
        if(obra != null) lista.addObra(obra);
        repository.salvar(lista);
    }

    public void excluirLista(ListaId id){
        repository.excluirPorID(id);
    }

    public ListaLeitura buscarListaPorID(ListaId id){
        return repository.buscarPorID(id);
    }

    public void adicionarLivro(ListaId id, Isbn livro){
        ListaLeitura lista = repository.buscarPorID(id);
        lista.addLivro(livro);
        repository.salvar(lista);
    }

    public void adicionarObra(ListaId id, DOI obra){
        ListaLeitura lista = repository.buscarPorID(id);
        lista.addObra(obra);
        repository.salvar(lista);
    }

    public void removerLivro(ListaId id, Isbn livro){
        ListaLeitura lista = repository.buscarPorID(id);
        lista.removeLivro(livro);
        repository.salvar(lista);
    }

    public void removerObra(ListaId id, DOI obra){
        ListaLeitura lista = repository.buscarPorID(id);
        lista.removeObra(obra);
        repository.salvar(lista);
    }

    public List<ListaLeitura> listarTodasLeiturasPorAluno(Matricula aluno){
        return repository.listarPorAluno(aluno);
    }

    public List<ListaLeitura> listarLeiturasPublicasPorAluno(Matricula aluno){
        return repository.listarPorAluno(aluno).stream().filter(ListaLeitura::isPublico).toList();
    }
}
