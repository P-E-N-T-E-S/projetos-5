package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExemplarImpl implements ExemplarRepository {

    private JpaMapeador mapeador;
    private ExemplarRepositorio exemplarRepositorio;

    public ExemplarImpl(JpaMapeador mapeador, ExemplarRepositorio exemplarRepositorio) {
        this.mapeador = mapeador;
        this.exemplarRepositorio = exemplarRepositorio;
    }

    @Override
    public void salvar(Exemplar exemplar) {
        var entidade = mapeador.map(exemplar, ExemplarJPA.class);
        exemplarRepositorio.save(entidade);
    }

    @Override
    public Exemplar buscarPorId(ExemplarId exemplarId) {
        var exemplarJPA = exemplarRepositorio.findById(exemplarId.getId()).orElse(null);
        if (exemplarJPA != null) {
            return mapeador.map(exemplarJPA, Exemplar.class);
        }
        return null;
    }

    @Override
    public List<Exemplar> buscarPorIsbn(String isbn) {
        List<ExemplarJPA> retorno = exemplarRepositorio.findByLivroIsbn(isbn);
        if (retorno != null) {
            return retorno.stream()
                    .map(jpa -> mapeador.map(jpa, Exemplar.class))
                    .toList();
        }
        return null;
    }
}
