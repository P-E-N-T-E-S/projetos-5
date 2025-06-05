package com.acervo.persistencia.memoria;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorRepository;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarRepository;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroRepository;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.ObraRepository;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoId;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoRepository;
import com.capibyte.acervo.dominio.core.administracao.salvo.LeituraRepository;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaId;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaLeitura;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.UsuarioRepository;
import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Repositorio implements AutorRepository, ExemplarRepository, LivroRepository, ObraRepository, SolicitacaoRepository, LeituraRepository, UsuarioRepository, ComentarioRepository {

    Map<Long, Exemplar> exemplars = new HashMap<>();
    Map<Isbn, Livro> livros = new HashMap<>();
    Map<Long, Solicitacao> solicitacoes = new HashMap<>();
    Map<String, Usuario> usuarios = new HashMap<>();
    Map<ListaId, ListaLeitura> listasDeLeitura = new HashMap<>();

    @Override
    public void salvar(Autor autor) {

    }

    @Override
    public Autor buscarPorNome(String nome) {
        return null;
    }

    @Override
    public Autor buscarPorId(AutorId id) {
        return null;
    }

    @Override
    public void salvar(Exemplar exemplar) {
        exemplars.put(exemplar.getCodigoDaObra().getId(), exemplar);
    }

    @Override
    public Exemplar buscarPorId(CodigoDaObra codigoDaObra) {
        return exemplars.get(codigoDaObra.getId());
    }

    @Override
    public List<Exemplar> buscarPorIsbn(String isbn) {
        return List.of();
    }

    @Override
    public void deletar(CodigoDaObra id) {

    }

    @Override
    public void salvar(Livro livro) {
        livros.put(livro.getIsbn(), livro);
    }

    @Override
    public void deletar(Isbn isbn) {

    }
    @Override
    public Livro buscarPorIsbn(Isbn isbn) {
        if (isbn != null) {
            return livros.get(isbn);
        }
        return null;
    }

    @Override
    public List<Livro> obterTodos() {
        List<Livro> livros_buscados = new ArrayList<>(livros.values());

        return livros_buscados;
    }

    @Override
    public List<Livro> buscarPorTema(String tema) {
        return List.of();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        for (Livro livro : livros.values()) {
            if (livro.getTitulo().equals(titulo)) {
                return List.of(livro);
            }
        }
        return null;
    }

    @Override
    public List<Livro> buscarPorAutor(String nomeAutor) {
        return List.of();
    }

    @Override
    public List<Livro> buscarPorAnoPublicacao(int anoInicio, int anoFim) {
        return List.of();
    }

    @Override
    public List<Livro> buscarPorNumeroChamada(String numeroChamada) {
        return List.of();
    }

    @Override
    public void salvar(Obra obra) {

    }

    @Override
    public Obra buscarPorId(DOI doi) {
        return null;
    }

    @Override
    public void deletar(DOI doi) {

    }

    @Override
    public List<Obra> obterTodosObra() {
        return List.of();
    }

    @Override
    public List<Obra> buscarPorPalavraChave(String palavraChave) {
        return List.of();
    }

    @Override
    public List<Obra> buscarPorValidado(boolean validado) {
        return List.of();
    }

    @Override
    public List<Obra> buscarObraPorTitulo(String titulo) {
        return List.of();
    }

    @Override
    public List<Obra> buscarObraPorAutor(String nomeAutor) {
        return List.of();
    }

    @Override
    public List<Obra> buscarPorDataPublicacao(LocalDate dataInicio, LocalDate dataFim) {
        return List.of();
    }

    @Override
    public void salvar(Solicitacao solicitacao) {
        solicitacoes.put(solicitacao.getId().getCodigo(), solicitacao);
    }

    @Override
    public List<Solicitacao> obterTodas() {
        return List.of();
    }

    @Override
    public Solicitacao buscarPorId(Long codigo) {
        return solicitacoes.get(codigo);
    }

    @Override
    public void deletar(Solicitacao solicitacao) {

    }

    @Override
    public void salvar(ListaLeitura leitura) {
        if (leitura != null && leitura.getId() != null) {
            listasDeLeitura.put(leitura.getId(), leitura);
        }
    }

    @Override
    public ListaLeitura buscarPorID(ListaId id) {
        if (id != null) {
            return listasDeLeitura.get(id);
        }
        return null;
    }

    @Override
    public void excluirPorID(ListaId id) {
        if (id != null) {
            listasDeLeitura.remove(id);
        }
    }

    @Override
    public List<ListaLeitura> listarPorAluno(Matricula aluno) {
        if (aluno == null) {
            return List.of();
        }
        return listasDeLeitura.values().stream()
                .filter(lista -> lista.getUsuario() != null && lista.getUsuario().equals(aluno))
                .collect(Collectors.toList());
    }

    @Override
    public void salvar(Usuario usuario) {
        usuarios.put(usuario.getMatricula().getCodigo(), usuario);
    }

    @Override
    public Usuario buscarPorMatricula(String matricula) {
        return usuarios.get(matricula);
    }

    @Override
    public void salvar(Comentario comentario) {

    }

    @Override
    public List<Comentario> listarPorIsbn(String isbn) {
        return List.of();
    }
}
