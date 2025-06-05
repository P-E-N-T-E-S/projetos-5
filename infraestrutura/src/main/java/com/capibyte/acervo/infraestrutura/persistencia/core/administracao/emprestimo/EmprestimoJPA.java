package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import jakarta.persistence.*;

@Embeddable
public class EmprestimoJPA {
    @ManyToOne
    @JoinColumn(name = "tomador_id")
    private UsuarioJPA tomador;
    @Embedded
    private PeriodoJPA periodo;

    public UsuarioJPA getTomador() {
        return tomador;
    }

    public void setTomador(UsuarioJPA tomador) {
        this.tomador = tomador;
    }

    public PeriodoJPA getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoJPA periodo) {
        this.periodo = periodo;
    }
}
