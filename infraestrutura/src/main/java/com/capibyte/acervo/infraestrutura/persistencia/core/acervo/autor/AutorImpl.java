package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorRepository;
import com.capibyte.acervo.infraestrutura.persistencia.JpaMapeador;
import org.springframework.stereotype.Repository;

@Repository
public class AutorImpl implements AutorRepository {

    private AutorJpaRepository autorRepository;

    private JpaMapeador mapeador;

    public AutorImpl(AutorJpaRepository autorRepository, JpaMapeador mapeador) {
        this.autorRepository = autorRepository;
        this.mapeador = mapeador;
    }

    public void salvar(Autor autor){
        AutorJPA autorJPA = mapeador.map(autor, AutorJPA.class);
        autorRepository.save(autorJPA);
    }

    public Autor buscarPorNome(String nome){
        return mapeador.map(autorRepository.findByNome(nome).orElse(null), Autor.class);
    }
}
