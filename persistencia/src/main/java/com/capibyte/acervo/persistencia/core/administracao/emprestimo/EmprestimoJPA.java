package com.capibyte.acervo.persistencia.core.administracao.emprestimo;

import com.capibyte.acervo.persistencia.core.administracao.usuario.UsuarioJPA;
import jakarta.persistence.*;

@Embeddable
public class EmprestimoJPA {
    @ManyToOne
    @JoinColumn(name = "tomador_id")
    private UsuarioJPA tomador;
    @Embedded
    private PeriodoJPA periodo;
}
