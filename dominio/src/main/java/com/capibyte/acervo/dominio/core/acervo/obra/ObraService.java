package com.capibyte.acervo.dominio.core.acervo.obra;

import org.springframework.stereotype.Service;

import com.capibyte.acervo.dominio.core.compartilhado.exceptions.ArquivoNaoDisponivelException;

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

    public void salvarPdf(DOI doi, byte[] pdf){
        Obra obra = repository.buscarPorId(doi);
        obra.setArquivoPdf(pdf);
        repository.salvar(obra);
    }

    public byte[] buscarPDF(DOI doi){
        Obra obra = repository.buscarPorId(doi);
        if(obra.getArquivoPdf() == null){
            throw new ArquivoNaoDisponivelException("Arquivo não disponível");
        }
        return obra.getArquivoPdf();
    }

    public List<Obra> buscarPorValidado(boolean validado){
        return repository.buscarPorValidado(validado);
    }

    public void validar(DOI doi){
        Obra obra = repository.buscarPorId(doi);
        obra.validar();
        repository.salvar(obra);
    }
}
