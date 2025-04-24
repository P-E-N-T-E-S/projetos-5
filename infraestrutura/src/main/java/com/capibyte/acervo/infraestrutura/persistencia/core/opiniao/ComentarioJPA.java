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

}
