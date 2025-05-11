package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.ObraRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ObraImpl implements ObraRepository {

    private ObraRepositorio repository;

    private JpaMapeador mapeador;

    public ObraImpl(ObraRepositorio repository, JpaMapeador mapeador) {
        this.repository = repository;
        this.mapeador = mapeador;
    }

    @Override
    public void salvar(Obra obra) {
        ObraJPA obraJPA = mapeador.map(obra, ObraJPA.class);
        repository.save(obraJPA);
    }

    @Override
    public Obra buscarPorId(DOI doi) {
        String codigo = doi.getCodigo(); //TODO: parar de ser dev
        return repository.findById(codigo).map(jpa -> mapeador.map(jpa, Obra.class)).orElse(null);
    }

    @Override
    @Transactional
    public void deletar(DOI doi) {
        repository.deleteById(doi.toString());
    }

    @Override
    public List<Obra> obterTodos() {
        return repository.findAll().stream().map(jpa -> mapeador.map(jpa, Obra.class)).toList() ;
    }

    @Override
    public List<Obra> buscarPorPalavraChave(String palavraChave) {
        return repository.findByPalavraChave(palavraChave).stream().map(jpa -> mapeador.map(jpa, Obra.class)).toList();
    }

    @Override
    public List<Obra> buscarPorValidado(boolean validado) {
        return repository.findByValidado(validado).stream().map(jpa -> mapeador.map(jpa, Obra.class)).toList() ;
    }


}
