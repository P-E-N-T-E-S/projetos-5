package com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo;

import jakarta.persistence.*;
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
    
    private String cargo;

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}