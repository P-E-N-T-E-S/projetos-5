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

    @ManyToOne()
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
}
