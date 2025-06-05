package com.capibyte.acervo.infraestrutura.persistencia.core.log;

import com.capibyte.acervo.dominio.log.PesquisaLogRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PesquisaLogImpl implements PesquisaLogRepository {

    private PesquisaLogRepositorio repository;

    public void salvar(String tema){
        PesquisaLog pesquisaLog = new PesquisaLog();
        pesquisaLog.setTema(tema);
        pesquisaLog.setDataPesquisa(LocalDateTime.now());
        repository.save(pesquisaLog);
    }
}
