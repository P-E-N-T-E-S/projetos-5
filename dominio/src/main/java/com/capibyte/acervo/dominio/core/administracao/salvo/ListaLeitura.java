package com.capibyte.acervo.dominio.core.administracao.salvo;

import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;

import java.util.ArrayList;
import java.util.List;

public class ListaLeitura {

    private ListaId id;
    private Matricula usuario;
    private String titulo;
    private String descricao;
    private List<Isbn> livros;
    private List<DOI> obras;
    private boolean privado;

    public ListaLeitura(Matricula usuario, String titulo, String descricao, boolean privado) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.livros = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.privado = privado;
    }

    public ListaLeitura(ListaId id, Matricula usuario, String titulo, String descricao, List<Isbn> livros, List<DOI> obras, boolean privado) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.livros = livros;
        this.obras = obras;
        this.privado = privado;
    }

    public ListaId getId() {
        return id;
    }

    public void setId(ListaId id) {
        this.id = id;
    }

    public Matricula getUsuario() {
        return usuario;
    }

    public void setUsuario(Matricula usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Isbn> getLivros() {
        return livros;
    }

    public void setLivros(List<Isbn> livros) {
        this.livros = livros;
    }

    public List<DOI> getObras() {
        return obras;
    }

    public void setObras(List<DOI> obras) {
        this.obras = obras;
    }

    public void addLivro(Isbn livro) {
        this.livros.add(livro);
    }

    public void addObra(DOI obra) {
        this.obras.add(obra);
    }

    public void removeLivro(Isbn livro) {
        this.livros.remove(livro);
    }

    public void removeObra(DOI obra) {
        this.obras.remove(obra);
    }
}
