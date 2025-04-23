package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    private ExemplarService exemplarService;

    public EmprestimoService(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

}
