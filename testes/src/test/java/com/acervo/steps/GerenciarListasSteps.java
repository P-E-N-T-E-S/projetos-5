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
        assertEquals(nomePasta, this.nomePastaParaCriar, "O nome da pasta fornecido no step 'And' não confere.");

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

        assertTrue(pastaCriadaOpt.isPresent(), "A pasta '" + nomePasta + "' criada não foi encontrada na lista do usuário.");
        this.pastaAtual = pastaCriadaOpt.get();
        assertNotNull(this.pastaAtual.getId(), "A pasta criada deveria ter um ID atribuído pelo sistema.");
        assertEquals(nomePasta, this.pastaAtual.getTitulo());
    }

    @And("a pasta {string} deve estar visível na minha lista de pastas")
    public void aPastaDeveEstarVisivelNaMinhaListaDePastas(String nomePasta) {
        assertNotNull(this.usuarioAtual, "Usuário atual não definido.");
        List<ListaLeitura> minhasPastas = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());
        assertNotNull(minhasPastas, "A lista de pastas do usuário não deveria ser nula.");

        boolean encontrada = minhasPastas.stream()
                .anyMatch(p -> p.getTitulo().equals(nomePasta) && p.getId().equals(this.pastaAtual.getId()));
        assertTrue(encontrada, "A pasta '" + nomePasta + "' (ID: " + (this.pastaAtual != null ? this.pastaAtual.getId() : "null") + ") não foi encontrada na lista de pastas do usuário.");
    }

    @And("a nova pasta deve estar disponível para adicionar obras posteriormente")
    public void aNovaPastaDeveEstarDisponivelParaAdicionarObras() {
        assertNotNull(this.pastaAtual, "Nenhuma pasta atual está definida para verificação.");
        assertNotNull(this.pastaAtual.getId(), "A pasta atual não possui um ID.");
        ListaLeitura pastaRecuperada = leituraService.buscarListaPorID(this.pastaAtual.getId());
        assertNotNull(pastaRecuperada, "Pasta não encontrada no sistema para verificação.");
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

        assertTrue(pastaCriadaOpt.isPresent(), "Não foi possível encontrar/criar a pasta de interesse '" + nomePasta + "'.");
        this.pastaAtual = pastaCriadaOpt.get();
        assertNotNull(this.pastaAtual.getId(), "A pasta de interesse '" + nomePasta + "' deveria ter um ID.");
    }

    @And("a obra {string} esteja disponível no catálogo do sistema")
    public void aObraEstejaDisponivelNoCatalogo(String nomeObra) {
        Isbn novoIsbn = gerarNovoIsbn(nomeObra);
        this.obraAtual = new Livro(novoIsbn, nomeObra, this.autores, "Sinopse de " + nomeObra, "1a", 2023, 100, this.temas);
        livroService.salvar(this.obraAtual);
        assertNotNull(livroService.buscarPorIsbn(novoIsbn), "Obra (Livro) '" + nomeObra + "' deveria estar disponível no catálogo.");
    }

    @When("eu selecionar a opção de adicionar a obra {string} à pasta {string}")
    public void euSelecionarAOpcaoDeAdicionarAObraAPasta(String nomeObra, String nomePasta) {
        assertNotNull(this.pastaAtual, "Nenhuma pasta de referência (this.pastaAtual) está definida.");
        assertEquals(nomePasta, this.pastaAtual.getTitulo(), "A pasta atual não é a esperada '" + nomePasta + "'.");
        assertNotNull(this.obraAtual, "A obra (Livro) '" + nomeObra + "' não foi definida em um passo anterior.");
        assertEquals(nomeObra, this.obraAtual.getTitulo(), "A obra atual não é a esperada '" + nomeObra + "'.");
        assertNotNull(this.pastaAtual.getId(), "A pasta atual não possui um ID para adicionar o livro.");
        assertNotNull(this.obraAtual.getIsbn(), "A obra atual não possui um ISBN.");

        leituraService.adicionarLivro(this.pastaAtual.getId(), this.obraAtual.getIsbn());
    }

    @Then("o sistema deve registrar a associação da obra {string} à pasta {string}")
    public void oSistemaDeveRegistrarAAssociacaoDaObraAPasta(String nomeObra, String nomePasta) {
        ListaLeitura pastaVerificada = leituraService.buscarListaPorID(this.pastaAtual.getId());
        assertNotNull(pastaVerificada, "Pasta '" + nomePasta + "' não encontrada após tentativa de associação.");
        assertNotNull(pastaVerificada.getLivros(), "A lista de livros da pasta não deveria ser nula.");
        assertTrue(pastaVerificada.getLivros().contains(this.obraAtual.getIsbn()),
                "A obra (Livro) '" + nomeObra + "' não foi associada corretamente à pasta '" + nomePasta + "'.");
        this.pastaAtual = pastaVerificada;
    }

    @And("a obra {string} deve aparecer listada dentro da pasta {string}")
    public void aObraDeveAparecerListadaDentroDaPasta(String nomeObra, String nomePasta) {
        assertNotNull(this.pastaAtual.getLivros(), "A lista de livros da pasta não deveria ser nula.");
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
        assertNotNull(this.pastaAtual, "Pasta '" + nomePasta + "' não foi encontrada após adicionar livros.");
        assertNotNull(this.pastaAtual.getLivros(), "Lista de livros da pasta '" + nomePasta + "' não deveria ser nula.");
        assertEquals(2, this.pastaAtual.getLivros().size(), "A pasta '" + nomePasta + "' deveria conter 2 livros.");
        assertTrue(this.pastaAtual.getLivros().contains(isbn1), "Livro '" + nomeObra1 + "' não encontrado na pasta.");
        assertTrue(this.pastaAtual.getLivros().contains(isbn2), "Livro '" + nomeObra2 + "' não encontrado na pasta.");
    }

    @When("eu acessar a pasta {string}")
    public void euAcessarAPasta(String nomePasta) {
        List<ListaLeitura> pastasDoUsuario = leituraService.listarTodasLeiturasPorAluno(this.usuarioAtual.getMatricula());
        Optional<ListaLeitura> pastaEncontradaOpt = pastasDoUsuario.stream()
                .filter(p -> p.getTitulo().equals(nomePasta))
                .findFirst();
        assertTrue(pastaEncontradaOpt.isPresent(), "A pasta '" + nomePasta + "' não foi encontrada para o usuário.");
        this.pastaAtual = pastaEncontradaOpt.get();
    }

    @Then("o sistema deve exibir as obras {string} e {string} guardadas nessa pasta")
    public void oSistemaDeveExibirAsObrasGuardadasNessaPasta(String nomeObra1, String nomeObra2) {
        assertNotNull(this.pastaAtual, "Nenhuma pasta está selecionada/acessada (this.pastaAtual é nulo).");

        assertNotNull(this.pastaAtual.getLivros(), "A lista de livros da pasta '" + this.pastaAtual.getTitulo() + "' não deveria ser nula.");
        assertEquals(2, this.pastaAtual.getLivros().size(), "A quantidade de livros na pasta '" + this.pastaAtual.getTitulo() + "' não é a esperada.");

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
        assertNotNull(this.pastaAtual, "Nenhuma pasta está selecionada (this.pastaAtual é nulo).");
        assertNotNull(this.pastaAtual.getLivros(), "A lista de livros da pasta atual não deveria ser nula.");
        assertFalse(this.pastaAtual.getLivros().isEmpty(), "A pasta '" + this.pastaAtual.getTitulo() + "' está vazia, não há obras para visualizar detalhes.");

        for (Isbn isbn : this.pastaAtual.getLivros()) {
            assertNotNull(isbn, "ISBN nulo encontrado na lista de livros da pasta '" + this.pastaAtual.getTitulo() + "'.");
            Livro livro = livroService.buscarPorIsbn(isbn);
            assertNotNull(livro, "Deveria ser possível buscar o livro pelo ISBN: " + isbn.getCodigo());
            assertNotNull(livro.getTitulo(), "O livro recuperado (ISBN: " + isbn.getCodigo() + ") deveria ter um título.");
        }
    }
}