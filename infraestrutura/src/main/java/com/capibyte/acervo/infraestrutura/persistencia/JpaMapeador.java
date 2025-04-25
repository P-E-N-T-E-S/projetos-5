package com.capibyte.acervo.infraestrutura.persistencia;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Periodo;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.ExemplarJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroRepositorio;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.EmprestimoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.PeriodoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaMapeador extends ModelMapper{

    @Autowired
    private LivroRepositorio livroRepositorio;

    JpaMapeador(){
        var configuracao = getConfiguration();
        configuracao.setFieldMatchingEnabled(true);
        configuracao.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        addConverter(new AbstractConverter<AutorJPA, Autor>() {
            protected Autor convert(AutorJPA source) {
                var id = map(source.getId(), AutorId.class);
                return new Autor(id, source.getNome());
            }
        });

        addConverter(new AbstractConverter<Integer, AutorId>() {

            @Override
            protected AutorId convert(Integer integer) {
                return new AutorId(integer);
            }
        });

        addConverter(new AbstractConverter<ExemplarJPA, Exemplar>() {
            protected Exemplar convert(ExemplarJPA source){
                var id = map(source.getExemplarId(), ExemplarId.class);
                var emprestimo = map(source.getEmprestimo(), Emprestimo.class);
                var livro = map(source.getLivro(), Isbn.class);
                return new Exemplar(id, livro, source.getLocalizacao(), emprestimo);
            }
        });

        addConverter(new AbstractConverter<PeriodoJPA, Periodo>() {
            @Override
            protected Periodo convert(PeriodoJPA source) {
                return new Periodo(source.getInicio(), source.getFim());
            }
        });

        addConverter(new AbstractConverter<EmprestimoJPA, Emprestimo>() {
            @Override
            protected Emprestimo convert(EmprestimoJPA source) {
                var periodo = map(source.getPeriodo(), Periodo.class);
                var matricula = map(source.getTomador(), Matricula.class);
                return new Emprestimo(periodo, matricula);
            }
        });

        addConverter( new AbstractConverter<Exemplar, ExemplarJPA>() {
            @Override
            protected ExemplarJPA convert(Exemplar source) {
                ExemplarJPA exemplarJPA = new ExemplarJPA();
                exemplarJPA.setExemplarId(source.getExemplarId().getId());
                exemplarJPA.setLivro(livroRepositorio.findByIsbn(source.getLivro().getCodigo()));
                exemplarJPA.setLocalizacao(source.getLocalizacao());
                if (source.getEmprestimo() != null) {
                    exemplarJPA.setEmprestimo(map(source.getEmprestimo(), EmprestimoJPA.class));
                }
                return exemplarJPA;
            }
        });

        addConverter(new AbstractConverter<Long, ExemplarId>() {
            @Override
            protected ExemplarId convert(Long source) {
                return new ExemplarId(source);
            }
        });

        addConverter(new AbstractConverter<UsuarioJPA, Usuario>() {
            @Override
            protected Usuario convert(UsuarioJPA source) {
                var matricula = map(source.getMatricula(), Matricula.class);
                var cargo = Cargo.fromIdentificador(source.getCargo());
                return new Usuario(matricula, source.getNome(), source.getEmail(), source.getSenha(), cargo);
            }
        });
    }
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return source != null ? super.map(source, destinationType) : null;
    }
}
