package com.capibyte.acervo.dominio.core.acervo.autor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void salvar(Autor autor){
        autorRepository.salvar(autor);
    }

    public Autor buscarPorNome(String nome){
        return autorRepository.buscarPorNome(nome);
    }

    public Autor buscarPorId(AutorId id){
        return autorRepository.buscarPorId(id);
    }

    public List<AutorId> processarEntrada(List<String> autores){
        return autores.stream().map(autor -> {
            Autor achado = buscarPorNome(autor);
            if(achado != null){
                return achado.id;
            }
            salvar(new Autor(null, autor));
            return buscarPorNome(autor).id;
        }).toList();
    }
}
