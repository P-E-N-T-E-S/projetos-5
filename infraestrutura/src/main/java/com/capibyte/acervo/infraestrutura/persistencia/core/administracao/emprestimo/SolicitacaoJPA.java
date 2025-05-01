package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "solicitacao")
public class SolicitacaoJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String matricula;
    
    @ElementCollection
    private List<Long> exemplarIds;

    LocalDate diaSolicitacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Long> getExemplarIds() {
        return exemplarIds;
    }

    public void setExemplarIds(List<Long> exemplarIds) {
        this.exemplarIds = exemplarIds;
    }

    public LocalDate getDiaSolicitacao() {
        return diaSolicitacao;
    }

    public void setDiaSolicitacao(LocalDate diaSolicitacao) {
        this.diaSolicitacao = diaSolicitacao;
    }
}