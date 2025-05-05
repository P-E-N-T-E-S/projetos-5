package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.ObraRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

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
        String codigo = doi.toString();
        return repository.findById(codigo).map(jpa -> mapeador.map(jpa, Obra.class)).orElse(null);
    }
}
