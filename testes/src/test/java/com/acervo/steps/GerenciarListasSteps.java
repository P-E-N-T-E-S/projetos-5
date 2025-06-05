package com.acervo.steps;

import com.acervo.persistencia.memoria.FuncionalidadesSistema;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaLeitura;
import com.capibyte.acervo.dominio.core.administracao.salvo.ListaId; // Still needed for method signatures
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import io.cucumber.java.en.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.*;

public class GerenciarListasSteps extends FuncionalidadesSistema {

    private Usuario usuarioAtual;
    private ListaLeitura pastaAtual;
    private Livro obraAtual;
    private String nomePastaParaCriar;
    private String descricaoPastaParaCriar;
    private boolean privadaPastaParaCriar;

    private static final AtomicLong livroIdCounter = new AtomicLong(System.currentTimeMillis());

    private List<AutorId> autores = new ArrayList<>();
    private List<String> temas = new ArrayList<>();

    private Isbn gerarNovoIsbn(String base) {
        return new Isbn(base.replaceAll("[^a-zA-Z0-9]", "") + "_" + livroIdCounter.getAndIncrement());
    }

    @Given("que eu seja um usuário autenticado no sistema")
    public void queEuSejaUmUsuarioAutenticadoNoSistema() {
        Matricula matricula = new Matricula("userLista" + livroIdCounter.getAndIncrement());
        this.usuarioAtual = new Usuario(matricula, "Usuário De Listas", "listas@example.com", "senha123", Cargo.GRADUANDO);
        usuarioService.salvar(this.usuarioAtual);
        assertNotNull(usuarioService.buscarPorMatricula(matricula.getCodigo()), "Usuário deveria estar salvo.");
    }

    @When("eu optar por criar uma nova pasta para guardar obras de meu interesse")
    public void euOptarPorCriarUmaNovaPasta() {
    }

    @And("eu fornecer o nome {string} para a pasta")
    public void euFornecerONomeParaAPasta(String nomePasta) {
        this.nomePastaParaCriar = nomePasta;
        this.descricaoPastaParaCriar = "Minha lista de " + nomePasta;
        this.privadaPastaParaCriar = true;
    }

    @Then("o sistema deve criar a pasta com o nome {string}")
    public void oSistemaDeveCriarAPastaComONome(String nomePasta) {
        leituraService.criarLista(
                this.usuarioAtual.getMatricula(),
                this.nomePastaParaCriar,
                this.descricaoPastaParaCriar,
                null,
                null,
                this.privadaPastaParaCriar
        );

        List<ListaLeitura> todasAsPastas = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());
        Optional<ListaLeitura> pastaCriadaOpt = todasAsPastas.stream()
                .filter(p -> p.getTitulo().equals(nomePasta))
                .max(Comparator.comparing(ListaLeitura::getId, Comparator.nullsLast(Comparator.comparing(ListaId::toString))));

        this.pastaAtual = pastaCriadaOpt.get();
        assertEquals(nomePasta, this.pastaAtual.getTitulo());
    }

    @And("a pasta {string} deve estar visível na minha lista de pastas")
    public void aPastaDeveEstarVisivelNaMinhaListaDePastas(String nomePasta) {
        List<ListaLeitura> minhasPastas = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());


        boolean encontrada = minhasPastas.stream()
                .anyMatch(p -> p.getTitulo().equals(nomePasta) && p.getId().equals(this.pastaAtual.getId()));
        assertTrue(encontrada, "A pasta '" + nomePasta + "' (ID: " + (this.pastaAtual != null ? this.pastaAtual.getId() : "null") + ") não foi encontrada na lista de pastas do usuário.");
    }

    @And("a nova pasta deve estar disponível para adicionar obras posteriormente")
    public void aNovaPastaDeveEstarDisponivelParaAdicionarObras() {
        ListaLeitura pastaRecuperada = leituraService.buscarListaPorID(this.pastaAtual.getId());
        assertTrue(pastaRecuperada.getLivros() == null || pastaRecuperada.getLivros().isEmpty(),
                "A lista de livros da nova pasta deveria estar vazia.");
        assertTrue(pastaRecuperada.getObras() == null || pastaRecuperada.getObras().isEmpty(),
                "A lista de obras (DOI) da nova pasta deveria estar vazia.");
    }

    @Given("que eu possua uma pasta de interesse chamada {string}")
    public void queEuPossuaUmaPastaDeInteresseChamada(String nomePasta) {
        String descricaoDefault = "Pasta existente: " + nomePasta;
        boolean privadaDefault = false;

        leituraService.criarLista(
                this.usuarioAtual.getMatricula(),
                nomePasta,
                descricaoDefault,
                null, null,
                privadaDefault
        );

        List<ListaLeitura> pastasDoUsuario = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());
        Optional<ListaLeitura> pastaCriadaOpt = pastasDoUsuario.stream()
                .filter(p -> p.getTitulo().equals(nomePasta))
                .max(Comparator.comparing(ListaLeitura::getId, Comparator.nullsLast(Comparator.comparing(ListaId::toString))));

        this.pastaAtual = pastaCriadaOpt.get();
    }

    @And("a obra {string} esteja disponível no catálogo do sistema")
    public void aObraEstejaDisponivelNoCatalogo(String nomeObra) {
        Isbn novoIsbn = gerarNovoIsbn(nomeObra);
        this.obraAtual = new Livro(novoIsbn, nomeObra, this.autores, "Sinopse de " + nomeObra, "1a", 2023, 100, this.temas);
        livroService.salvar(this.obraAtual);
    }

    @When("eu selecionar a opção de adicionar a obra {string} à pasta {string}")
    public void euSelecionarAOpcaoDeAdicionarAObraAPasta(String nomeObra, String nomePasta) {
        leituraService.adicionarLivro(this.pastaAtual.getId(), this.obraAtual.getIsbn());
    }

    @Then("o sistema deve registrar a associação da obra {string} à pasta {string}")
    public void oSistemaDeveRegistrarAAssociacaoDaObraAPasta(String nomeObra, String nomePasta) {
        ListaLeitura pastaVerificada = leituraService.buscarListaPorID(this.pastaAtual.getId());
        assertTrue(pastaVerificada.getLivros().contains(this.obraAtual.getIsbn()),
                "A obra (Livro) '" + nomeObra + "' não foi associada corretamente à pasta '" + nomePasta + "'.");
        this.pastaAtual = pastaVerificada;
    }

    @And("a obra {string} deve aparecer listada dentro da pasta {string}")
    public void aObraDeveAparecerListadaDentroDaPasta(String nomeObra, String nomePasta) {
        assertTrue(this.pastaAtual.getLivros().contains(this.obraAtual.getIsbn()),
                "A obra (Livro) '" + nomeObra + "' não está listada dentro da pasta '" + nomePasta + "'.");
    }

    @Given("que a pasta {string} contenha as obras {string} e {string} adicionadas previamente")
    public void queAPastaContenhaObrasAdicionadasPreviamente(String nomePasta, String nomeObra1, String nomeObra2) {
        leituraService.criarLista(this.usuarioAtual.getMatricula(), nomePasta, "Pasta com: " + nomeObra1 + " & " + nomeObra2, null, null, true);
        List<ListaLeitura> pastasDoUsuario = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());
        Optional<ListaLeitura> pastaCriadaOpt = pastasDoUsuario.stream()
                .filter(p -> p.getTitulo().equals(nomePasta))
                .max(Comparator.comparing(ListaLeitura::getId, Comparator.nullsLast(Comparator.comparing(ListaId::toString))));
        assertTrue(pastaCriadaOpt.isPresent(), "Não foi possível criar/encontrar a pasta '" + nomePasta + "'.");
        this.pastaAtual = pastaCriadaOpt.get();
        ListaId idDaPasta = this.pastaAtual.getId();

        Isbn isbn1 = gerarNovoIsbn(nomeObra1);
        Livro obra1 = new Livro(isbn1, nomeObra1, this.autores, "Sinopse " + nomeObra1, "1a", 2024, 110, this.temas);
        livroService.salvar(obra1);

        Isbn isbn2 = gerarNovoIsbn(nomeObra2);
        Livro obra2 = new Livro(isbn2, nomeObra2, this.autores, "Sinopse " + nomeObra2, "1a", 2024, 120, this.temas);
        livroService.salvar(obra2);

        leituraService.adicionarLivro(idDaPasta, isbn1);
        leituraService.adicionarLivro(idDaPasta, isbn2);

        this.pastaAtual = leituraService.buscarListaPorID(idDaPasta);
    }

    @When("eu acessar a pasta {string}")
    public void euAcessarAPasta(String nomePasta) {
        List<ListaLeitura> pastasDoUsuario = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());
        Optional<ListaLeitura> pastaEncontradaOpt = pastasDoUsuario.stream()
                .filter(p -> p.getTitulo().equals(nomePasta))
                .findFirst();
        this.pastaAtual = pastaEncontradaOpt.get();
    }

    @Then("o sistema deve exibir as obras {string} e {string} guardadas nessa pasta")
    public void oSistemaDeveExibirAsObrasGuardadasNessaPasta(String nomeObra1, String nomeObra2) {

        List<String> titulosNaPasta = new ArrayList<>();
        for (Isbn isbn : this.pastaAtual.getLivros()) {
            Livro livro = livroService.buscarPorIsbn(isbn);
            if (livro != null) {
                titulosNaPasta.add(livro.getTitulo());
            } else {
                fail("Livro com ISBN " + (isbn != null ? isbn.getCodigo() : "null") + " não encontrado no catálogo, mas estava na lista '" + this.pastaAtual.getTitulo() + "'.");
            }
        }

        assertTrue(titulosNaPasta.contains(nomeObra1), "Obra (Livro) '" + nomeObra1 + "' não encontrada na pasta '" + this.pastaAtual.getTitulo() + "'. Encontrados: " + titulosNaPasta);
        assertTrue(titulosNaPasta.contains(nomeObra2), "Obra (Livro) '" + nomeObra2 + "' não encontrada na pasta '" + this.pastaAtual.getTitulo() + "'. Encontrados: " + titulosNaPasta);
    }

    @And("devo ter a opção de visualizar os detalhes de cada obra listada")
    public void devoTerAOpcaoDeVisualizarOsDetalhesDeCadaObraListada() {
        for (Isbn isbn : this.pastaAtual.getLivros()) {
            Livro livro = livroService.buscarPorIsbn(isbn);
        }
    }
}