package com.capibyte.acervo.dominio.core.acervo.exemplar;

import org.springframework.stereotype.Service;

@Service
public class ExemplarService {

    private ExemplarRepository exemplarRepository;

    public ExemplarService(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }

    public void salvar(Exemplar exemplar){
        exemplarRepository.salvar(exemplar);
    }

    public Exemplar buscarPorId(CodigoDaObra id){
        return exemplarRepository.buscarPorId(id);
    }

    public void deletar(CodigoDaObra id){
        exemplarRepository.deletar(id);
    }
}
