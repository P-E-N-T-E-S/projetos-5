package com.capibyte.acervo.dominio.core.acervo.obra;

import org.springframework.stereotype.Service;

@Service
public class ObraService {

    private ObraRepository repository;

    public ObraService(ObraRepository repository) {
        this.repository = repository;
    }

    public void salvar(Obra obra) {
        repository.salvar(obra);
    }

    public Obra buscarPorId(DOI doi) {
        return repository.buscarPorId(doi);
    }

    public void deletar(DOI doi){
        repository.deletar(doi);
    }
}
