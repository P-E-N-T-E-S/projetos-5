package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.salvo;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra.ObraJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ListaLeituraJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario_matricula", nullable = false)
    private UsuarioJPA usuario;

    private String titulo;
    private String descricao;
    @ManyToMany()
    @JoinTable(
            name = "lista_livro",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "isbn")
    )
    private List<LivroJPA> livros;
    @ManyToMany()
    @JoinTable(
            name = "lista_obra",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "doi")
    )
    private List<ObraJPA> obras;
    private boolean privado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UsuarioJPA getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioJPA usuario) {
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

    public List<LivroJPA> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroJPA> livros) {
        this.livros = livros;
    }

    public List<ObraJPA> getObras() {
        return obras;
    }

    public void setObras(List<ObraJPA> obras) {
        this.obras = obras;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }
}
