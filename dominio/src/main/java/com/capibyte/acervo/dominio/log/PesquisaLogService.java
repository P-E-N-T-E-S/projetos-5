package com.capibyte.acervo.dominio.log;

import org.springframework.stereotype.Service;

@Service
public class PesquisaLogService {

    private  PesquisaLogRepository pesquisaLogRepository;

    public PesquisaLogService(PesquisaLogRepository pesquisaLogRepository) {
        this.pesquisaLogRepository = pesquisaLogRepository;
    }

    public void salvar(String tema) {
        pesquisaLogRepository.salvar(tema);
    }
}
