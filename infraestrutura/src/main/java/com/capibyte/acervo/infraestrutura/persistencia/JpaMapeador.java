package com.capibyte.acervo.infraestrutura.persistencia;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorRepository;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Localizacao;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Periodo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoId;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJpaRepository;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.ExemplarJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.LocalizacaoJpa;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroRepositorio;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.EmprestimoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.PeriodoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.SolicitacaoJPA;
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

    @Autowired
    private AutorJpaRepository autorJpaRepository;

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

        addConverter(new AbstractConverter<Long, AutorId>() {

            @Override
            protected AutorId convert(Long integer) {
                return new AutorId(integer);
            }
        });

        addConverter(new AbstractConverter<ExemplarJPA, Exemplar>() {
            protected Exemplar convert(ExemplarJPA source){
                var id = map(source.getCodigoDaObra(), CodigoDaObra.class);
                var emprestimo = map(source.getEmprestimo(), Emprestimo.class);
                var livro = new Isbn(source.getLivro().getIsbn());
                Localizacao localizacao = new Localizacao(source.getLocalizacao().getPrateleira(), source.getLocalizacao().getAndar());
                return new Exemplar(id, livro, localizacao, emprestimo, source.getStatus());
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

        addConverter(new AbstractConverter<Exemplar, ExemplarJPA>() {
            @Override
            protected ExemplarJPA convert(Exemplar source) {
                ExemplarJPA exemplarJPA = new ExemplarJPA();
                exemplarJPA.setCodigoDaObra(source.getCodigoDaObra().getId());
                exemplarJPA.setLivro(livroRepositorio.findByIsbn(source.getLivro().getCodigo()));
                LocalizacaoJpa localizacaoJpa = new LocalizacaoJpa();
                localizacaoJpa.setAndar(source.getLocalizacao().getAndar());
                localizacaoJpa.setPrateleira(source.getLocalizacao().getPrateleira());
                exemplarJPA.setLocalizacao(localizacaoJpa);
                exemplarJPA.setStatus(source.getStatus());
                if (source.getEmprestimo() != null) {
                    exemplarJPA.setEmprestimo(map(source.getEmprestimo(), EmprestimoJPA.class));
                }
                return exemplarJPA;
            }
        });


        addConverter(new AbstractConverter<Long, CodigoDaObra>() {
            @Override
            protected CodigoDaObra convert(Long source) {
                return new CodigoDaObra(source);
            }
        });

        addConverter(new AbstractConverter<UsuarioJPA, Usuario>() {
            @Override
            protected Usuario convert(UsuarioJPA source) {
                Matricula matricula = new Matricula(source.getMatricula());
                Cargo cargo = Cargo.fromIdentificador(source.getCargo());
                return new Usuario(matricula, source.getNome(), source.getEmail(), source.getSenha(), cargo);
            }
        });

        addConverter(new AbstractConverter<Usuario, UsuarioJPA>() {
            protected UsuarioJPA convert(Usuario source) {
                UsuarioJPA usuarioJPA = new UsuarioJPA();
                usuarioJPA.setCargo(source.getCargo().getIdentificador());
                usuarioJPA.setEmail(source.getEmail());
                usuarioJPA.setMatricula(source.getMatricula().toString());
                usuarioJPA.setNome(source.getNome());
                usuarioJPA.setSenha(source.getSenha());
                return usuarioJPA;
            }
        });

        addConverter(new AbstractConverter<Solicitacao, SolicitacaoJPA>() {
            @Override
            protected SolicitacaoJPA convert(Solicitacao source) {
                SolicitacaoJPA solicitacaoJPA = new SolicitacaoJPA();
                solicitacaoJPA.setDiaSolicitacao(source.getDiaSolicitacao());
                solicitacaoJPA.setExemplarIds(source.getExemplares().stream().map( exemplarId -> exemplarId.getId() ).toList());
                solicitacaoJPA.setMatricula(source.getTomador().toString());
                return solicitacaoJPA;
            }
        });

        addConverter(new AbstractConverter<SolicitacaoJPA, Solicitacao>() {
            @Override
            protected Solicitacao convert(SolicitacaoJPA source) {
                return new Solicitacao(new SolicitacaoId(source.getId()), new Matricula(source.getMatricula()), source.getDiaSolicitacao(), source.getExemplarIds().stream().map(CodigoDaObra::new).toList());
            }
        });

        addConverter(new AbstractConverter<Livro, LivroJPA>() {
            @Override
            protected LivroJPA convert(Livro source) {
                LivroJPA livroJPA = new LivroJPA();
                livroJPA.setIsbn(source.getIsbn().getCodigo());
                livroJPA.setTitulo(source.getTitulo());
                livroJPA.setSinopse(source.getSinpose());
                livroJPA.setNumeroChamada(source.getNumeroChamada());
                livroJPA.setAnoDePublicacao(source.getAnoDePublicacao());
                livroJPA.setQuantidadeDePaginas(source.getQuantidadeDePaginas());
                livroJPA.setTemas(source.getTemas());
                livroJPA.setAutores(source.getAutores().stream().map(autor -> autorJpaRepository.findById(autor.getId()).orElse(null)).toList());
                return livroJPA;
            }
        });

        addConverter(new AbstractConverter<LivroJPA, Livro>() {
            @Override
            protected Livro convert(LivroJPA source) {
                return new Livro(
                        new Isbn(source.getIsbn()),
                        source.getTitulo(),
                        source.getAutores().stream().map(autor -> new AutorId(autor.getId())).toList(),
                        source.getSinopse(),
                        source.getNumeroChamada(),
                        source.getAnoDePublicacao(),
                        source.getQuantidadeDePaginas(),
                        source.getTemas()
                );
            }
        });
    }
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return source != null ? super.map(source, destinationType) : null;
    }
}
