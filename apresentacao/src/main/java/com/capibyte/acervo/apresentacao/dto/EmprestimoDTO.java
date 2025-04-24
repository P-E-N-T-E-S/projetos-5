package com.capibyte.acervo.apresentacao.dto;

public class EmprestimoDTO {
    private Long exemplarId;
    private String matricula;

    public EmprestimoDTO(Long exemplarId, String matricula) {
        this.exemplarId = exemplarId;
        this.matricula = matricula;
    }

    public Long getExemplarId() {
        return exemplarId;
    }

    public String getMatricula() {
        return matricula;
    }
}
