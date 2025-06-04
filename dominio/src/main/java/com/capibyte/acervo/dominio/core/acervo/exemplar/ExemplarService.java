package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.exemplar.enums.Status;
import com.capibyte.acervo.dominio.core.acervo.exemplar.exceptions.ExemplarNaoEncontradoException;
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

    public void  definirStatus(CodigoDaObra id, int status){
        Status statusEnum = Status.getStatus(status);
        Exemplar exemplar = exemplarRepository.buscarPorId(id);
        if(exemplar == null){
            throw new ExemplarNaoEncontradoException("Exemplar nao encontrado");
        }
        exemplar.setStatus(statusEnum);
        exemplarRepository.salvar(exemplar);
    }
}
