package com.capibyte.acervo.infraestrutura.persistencia.core.opiniao;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comentario")
public class ComentarioJPA {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "isbn", nullable = false)
    private LivroJPA livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioJPA usuario;

    private LocalDateTime dataCriacao;
    private String conteudo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LivroJPA getLivro() {
        return livro;
    }

    public void setLivro(LivroJPA livro) {
        this.livro = livro;
    }

    public UsuarioJPA getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioJPA usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
