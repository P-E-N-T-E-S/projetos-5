package com.capibyte.acervo.infraestrutura.persistencia;

import com.capibyte.acervo.dominio.core.acervo.autor.Autor;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Localizacao;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.PalavraChave;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Emprestimo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Periodo;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.Solicitacao;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.SolicitacaoId;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaId;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaLeitura;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import com.capibyte.acervo.dominio.core.opiniao.ComentarioId;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJpaRepository;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.ExemplarJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.LocalizacaoJpa;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroRepositorio;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra.ObraJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra.ObraRepositorio;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.EmprestimoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.PeriodoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.SolicitacaoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.salvo.ListaLeituraJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJpaRepository;
import com.capibyte.acervo.infraestrutura.persistencia.core.opiniao.ComentarioJPA;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaMapeador extends ModelMapper{

    @Autowired
    private LivroRepositorio livroRepositorio;

    @Autowired
    private AutorJpaRepository autorJpaRepository;

    @Autowired
    private UsuarioJpaRepository usuarioRepositorio;

    @Autowired
    private ObraRepositorio obraRepositorio;

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
                solicitacaoJPA.setExemplarIds(source.getExemplares().stream().map(CodigoDaObra::getId).toList());
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

        addConverter(new AbstractConverter<Comentario, ComentarioJPA>() {
            @Override
            protected ComentarioJPA convert(Comentario source) {
                ComentarioJPA comentarioJPA = new ComentarioJPA();
                comentarioJPA.setId(source.getId() != null ? source.getId().getId() : 0);
                comentarioJPA.setConteudo(source.getConteudo());
                comentarioJPA.setDataCriacao(source.getDataCriacao());

                LivroJPA livroJPA = livroRepositorio.findByIsbn(source.getIsbn().getCodigo());
                comentarioJPA.setLivro(livroJPA);

                UsuarioJPA usuarioJPA = usuarioRepositorio.findByMatricula(source.getUsuario().toString());
                comentarioJPA.setUsuario(usuarioJPA);

                return comentarioJPA;
            }
        });

        addConverter(new AbstractConverter<ComentarioJPA, Comentario>() {
            @Override
            protected Comentario convert(ComentarioJPA source) {
                return new Comentario(
                        new ComentarioId(source.getId()),
                        new Isbn(source.getLivro().getIsbn()),
                        source.getConteudo(),
                        source.getDataCriacao(),
                        new Matricula(source.getUsuario().getMatricula())
                );
            }
        });

        // Adicione esses conversores dentro do construtor JpaMapeador()

// Conversão de PalavraChave para String
        addConverter(new AbstractConverter<PalavraChave, String>() {
            @Override
            protected String convert(PalavraChave source) {
                return source.toString();
            }
        });

// Conversão de String para PalavraChave
        addConverter(new AbstractConverter<String, PalavraChave>() {
            @Override
            protected PalavraChave convert(String source) {
                return new PalavraChave(source);
            }
        });

// Conversão de DOI para String
        addConverter(new AbstractConverter<DOI, String>() {
            @Override
            protected String convert(DOI source) {
                return source.getCodigo();
            }
        });

// Conversão de String para DOI
        addConverter(new AbstractConverter<String, DOI>() {
            @Override
            protected DOI convert(String source) {
                return new DOI(source);
            }
        });

// Conversão de Obra para ObraJPA
        addConverter(new AbstractConverter<Obra, ObraJPA>() {
            @Override
            protected ObraJPA convert(Obra source) {
                var obraJPA = new ObraJPA();
                obraJPA.setDoi(map(source.getDoi(), String.class));
                obraJPA.setTitulo(source.getTitulo());
                obraJPA.setResumo(source.getResumo());
                obraJPA.setDataPublicacao(source.getDataPublicacao());
                obraJPA.setCitacaoAbnt(source.getCitacaoAbnt());
                obraJPA.setArquivoPDF(source.getArquivoPdf());


                // Converte palavras-chave
                var palavrasChave = source.getPalavrasChave().stream()
                        .map(pc -> map(pc, String.class))
                        .collect(Collectors.toList());
                obraJPA.setPalavrasChave(palavrasChave);

                // Converte autores
                var autores = source.getAutores().stream()
                        .map(autorId -> autorJpaRepository.findById(autorId.getId())
                                .orElseThrow(() -> new RuntimeException("Autor não encontrado: " + autorId)))
                        .collect(Collectors.toList());
                obraJPA.setAutoresObra(autores);

                return obraJPA;
            }
        });

// Conversão de ObraJPA para Obra
        addConverter(new AbstractConverter<ObraJPA, Obra>() {
            @Override
            protected Obra convert(ObraJPA source) {
                var doi = map(source.getDoi(), DOI.class);

                // Converte autores
                var autores = source.getAutoresObra().stream()
                        .map(autorJPA -> new AutorId(autorJPA.getId()))
                        .collect(Collectors.toList());

                // Converte palavras-chave
                var palavrasChave = source.getPalavrasChave().stream()
                        .map(pc -> map(pc, PalavraChave.class))
                        .collect(Collectors.toList());

                return new Obra(
                        doi,
                        source.getTitulo(),
                        autores,
                        palavrasChave,
                        source.getResumo(),
                        source.getDataPublicacao(),
                        source.getCitacaoAbnt(),
                        source.getArquivoPDF()
                );
            }
        });

        addConverter(new AbstractConverter<ListaLeituraJPA, ListaLeitura>() {
            @Override
            protected ListaLeitura convert(ListaLeituraJPA entity) {
                if (entity == null) return null;
                Matricula matricula = new Matricula(entity.getUsuario().getMatricula());
                ListaId listaId = new ListaId(entity.getId());

                List<Isbn> livros = new ArrayList<>();
                if (entity.getLivros() != null) {
                    for (LivroJPA livroJPA : entity.getLivros()) {
                        livros.add(new Isbn(livroJPA.getIsbn()));
                    }
                }

                List<DOI> obras = new ArrayList<>();
                if (entity.getObras() != null) {
                    for (ObraJPA obraJPA : entity.getObras()) {
                        obras.add(new DOI(obraJPA.getDoi()));
                    }
                }

                return new ListaLeitura(
                        listaId,
                        matricula,
                        entity.getTitulo(),
                        entity.getDescricao(),
                        livros,
                        obras,
                        entity.isPrivado()
                );
            }
        });

        addConverter(new AbstractConverter<ListaLeitura, ListaLeituraJPA>() {
            @Override
            protected ListaLeituraJPA convert(ListaLeitura domain) {
                if (domain == null) return null;
                ListaLeituraJPA entity = new ListaLeituraJPA();

                if (domain.getId() != null) {
                    entity.setId(domain.getId().getCodigo());
                }
                UsuarioJPA usuarioJPA = usuarioRepositorio.findByMatricula(domain.getUsuario().getCodigo());
                entity.setUsuario(usuarioJPA);

                entity.setTitulo(domain.getTitulo());
                entity.setDescricao(domain.getDescricao());
                entity.setPrivado(!domain.isPublico());

                if (domain.getLivros() != null) {
                    List<LivroJPA> livrosJPA = new ArrayList<>();
                    for (Isbn isbn : domain.getLivros()) {
                        LivroJPA livroJPA = livroRepositorio.findByIsbn(isbn.getCodigo());
                        if (livroJPA != null) livrosJPA.add(livroJPA);
                    }
                    entity.setLivros(livrosJPA);
                }

                if (domain.getObras() != null) {
                    List<ObraJPA> obrasJPA = new ArrayList<>();
                    for (DOI doi : domain.getObras()) {
                        obraRepositorio.findById(doi.getCodigo()).ifPresent(obrasJPA::add);
                    }
                    entity.setObras(obrasJPA);
                }

                return entity;
            }
        });


    }
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return source != null ? super.map(source, destinationType) : null;
    }
}
