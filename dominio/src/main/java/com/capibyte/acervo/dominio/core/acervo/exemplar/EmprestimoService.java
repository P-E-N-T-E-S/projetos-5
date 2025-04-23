package com.capibyte.acervo.dominio.core.acervo.exemplar;

import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    private ExemplarService exemplarService;

    public EmprestimoService(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    public void realizarEmprestimo(ExemplarId exemplarId, Matricula tomador){
        Exemplar exemplar = exemplarService.buscarPorId(exemplarId);
        exemplar.alugar(tomador);
        exemplarService.salvar(exemplar);
    }
}
