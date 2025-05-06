package com.capibyte.acervo.dominio.core.acervo.obra;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Obra> listarTodos(){
        return repository.obterTodos();
    }

    public List<Obra> buscarPorPalavraChave(String palavraChave){
        return repository.buscarPorPalavraChave(palavraChave);
    }

    public byte[] buscarPDF(DOI doi){
        Obra obra = repository.buscarPorId(doi);
        return obra.getArquivoPdf();
    }
}
