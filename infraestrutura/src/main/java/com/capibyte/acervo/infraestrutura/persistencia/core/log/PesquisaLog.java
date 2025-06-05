package com.capibyte.acervo.infraestrutura.persistencia.core.log;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PesquisaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO: solução temporária, vamos implementar uma messageria e um listener para guardar esses dados
    private Long id;

    private String tema;

    private LocalDateTime dataPesquisa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDateTime getDataPesquisa() {
        return dataPesquisa;
    }

    public void setDataPesquisa(LocalDateTime dataPesquisa) {
        this.dataPesquisa = dataPesquisa;
    }
}
