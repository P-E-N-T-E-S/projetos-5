package com.capibyte.acervo.dominio.core.administracao.emprestimo;

import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    private ExemplarService exemplarService;

    public EmprestimoService(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    public void realizarEmprestimo(ExemplarId exemplarId, Matricula tomador, Cargo cargo){
        Exemplar exemplar = exemplarService.buscarPorId(exemplarId);
        exemplar.alugar(tomador, cargo);
        exemplarService.salvar(exemplar);
    }
}
