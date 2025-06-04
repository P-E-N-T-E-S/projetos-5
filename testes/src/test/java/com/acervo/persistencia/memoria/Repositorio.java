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
import java.util.List;

public class Repositorio implements AutorRepository, ExemplarRepository, LivroRepository, ObraRepository, SolicitacaoRepository, LeituraRepository, UsuarioRepository, ComentarioRepository {

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

    }

    @Override
    public Exemplar buscarPorId(CodigoDaObra codigoDaObra) {
        return null;
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

    }

    @Override
    public void deletar(Isbn isbn) {

    }

    @Override
    public Livro buscarPorIsbn(Isbn isbn) {
        return null;
    }

    @Override
    public List<Livro> obterTodos() {
        return List.of();
    }

    @Override
    public List<Livro> buscarPorTema(String tema) {
        return List.of();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        return List.of();
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

    }

    @Override
    public List<Solicitacao> obterTodas() {
        return List.of();
    }

    @Override
    public Solicitacao buscarPorId(Long codigo) {
        return null;
    }

    @Override
    public void deletar(Solicitacao solicitacao) {

    }

    @Override
    public void salvar(ListaLeitura leitura) {

    }

    @Override
    public ListaLeitura buscarPorID(ListaId id) {
        return null;
    }

    @Override
    public void excluirPorID(ListaId id) {

    }

    @Override
    public List<ListaLeitura> listarPorAluno(Matricula aluno) {
        return List.of();
    }

    @Override
    public void salvar(Usuario usuario) {

    }

    @Override
    public Usuario buscarPorMatricula(String matricula) {
        return null;
    }

    @Override
    public void salvar(Comentario comentario) {

    }

    @Override
    public List<Comentario> listarPorIsbn(String isbn) {
        return List.of();
    }
}
