package com.acervo.steps;

import com.acervo.persistencia.memoria.FuncionalidadesSistema;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import io.cucumber.datatable.DataTable; // Import necessário para DataTable
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FiltrarObrasSteps extends FuncionalidadesSistema {

    // Variáveis para guardar o estado dentro de um cenário
    private String temaFiltro;
    private String tituloFiltro;
    private Integer anoInicioFiltro;
    private Integer anoFimFiltro;
    private List<Livro> resultadoBusca;
    private static final AtomicLong contador = new AtomicLong(System.currentTimeMillis());

    @Before
    public void setUp() {
        temaFiltro = null;
        tituloFiltro = null;
        anoInicioFiltro = null;
        anoFimFiltro = null;
        resultadoBusca = null;
    }

    @Given("que eu seja um usuário autenticada no sistema")
    public void queEuSejaUmUsuarioAutenticadoNoSistema() {
        usuarioService.salvar(new Usuario(new Matricula("userFiltro"), "User Filtro", "filtro@test.com", "123", Cargo.GRADUANDO));
    }

    @Given("que as seguintes obras existam no catálogo:")
    public void queAsSeguintesObrasExistamNoCatalogo(DataTable dataTable) {
        List<Map<String, String>> obras = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> obra : obras) {
            String titulo = obra.get("titulo");
            String tema = obra.get("tema");
            int anoPublicacao = Integer.parseInt(obra.get("ano_publicacao"));

            Isbn isbn = new Isbn("ISBN-" + titulo.replaceAll(" ", "") + contador.getAndIncrement());
            List<AutorId> autores = new ArrayList<>();
            List<String> temas = List.of(tema);

            Livro novoLivro = new Livro(isbn, titulo, autores, "Sinopse para " + titulo, "1ed", anoPublicacao, 200, temas);
            livroService.salvar(novoLivro);
        }
    }

    @When("eu selecionar o filtro {string} e escolher o valor {string}")
    public void euSelecionarOFiltroEEscolherOValor(String tipoFiltro, String valor) {
        if ("Tema".equalsIgnoreCase(tipoFiltro)) {
            this.temaFiltro = valor;
        } else {
            fail("Tipo de filtro '" + tipoFiltro + "' não é suportado neste step.");
        }
    }

    @When("eu utilizar o filtro {string} com o valor {string}")
    public void euUtilizarOFiltroComOValor(String tipoFiltro, String valor) {
        if ("Título".equalsIgnoreCase(tipoFiltro)) {
            this.tituloFiltro = valor;
        } else {
            fail("Tipo de filtro '" + tipoFiltro + "' não é suportado neste step.");
        }
    }

    @And("eu utilizar o filtro {string} com o intervalo de {string} a {string}")
    public void euUtilizarOFiltroComOIntervaloDeA(String tipoFiltro, String anoInicio, String anoFim) {
        if ("Data de Postagem".equalsIgnoreCase(tipoFiltro)) { // "Data de Postagem" mapeada para ano de publicação
            this.anoInicioFiltro = Integer.parseInt(anoInicio);
            this.anoFimFiltro = Integer.parseInt(anoFim);
        } else {
            fail("Tipo de filtro '" + tipoFiltro + "' não é suportado neste step.");
        }
    }

    private void executarBuscaComFiltros() {
        List<Livro> todosOsLivros = livroService.obterTodos();

        this.resultadoBusca = todosOsLivros.stream()
                .filter(livro -> temaFiltro == null || (livro.getTemas() != null && livro.getTemas().contains(temaFiltro)))
                .filter(livro -> tituloFiltro == null || livro.getTitulo().toLowerCase().contains(tituloFiltro.toLowerCase()))
                .filter(livro -> anoInicioFiltro == null || livro.getAnoDePublicacao() >= anoInicioFiltro)
                .filter(livro -> anoFimFiltro == null || livro.getAnoDePublicacao() <= anoFimFiltro)
                .collect(Collectors.toList());
    }

    @Then("o sistema deve exibir as obras {string} e {string}")
    public void oSistemaDeveExibirAsObras(String titulo1, String titulo2) {
        executarBuscaComFiltros();

        assertNotNull(resultadoBusca, "O resultado da busca não deveria ser nulo.");
        assertEquals(2, resultadoBusca.size(), "A busca deveria retornar exatamente 2 obras.");

        List<String> titulosEncontrados = resultadoBusca.stream().map(Livro::getTitulo).collect(Collectors.toList());
        assertTrue(titulosEncontrados.contains(titulo1), "A obra '" + titulo1 + "' não foi encontrada.");
        assertTrue(titulosEncontrados.contains(titulo2), "A obra '" + titulo2 + "' não foi encontrada.");
    }

    @Then("o sistema deve exibir somente a obra {string}")
    public void oSistemaDeveExibirSomenteAObra(String tituloEsperado) {
        executarBuscaComFiltros();

        assertNotNull(resultadoBusca, "O resultado da busca não deveria ser nulo.");
        assertEquals(1, resultadoBusca.size(), "A busca deveria retornar somente 1 obra.");
        assertEquals(tituloEsperado, resultadoBusca.get(0).getTitulo(), "A obra retornada não é a esperada.");
    }

    @And("o sistema não deve exibir a obra {string}")
    public void oSistemaNaoDeveExibirAObra(String tituloNaoEsperado) {
        assertNotNull(resultadoBusca, "Não há resultado de busca para ser verificado.");

        boolean encontrou = resultadoBusca.stream()
                .anyMatch(livro -> livro.getTitulo().equalsIgnoreCase(tituloNaoEsperado));

        assertFalse(encontrou, "A obra '" + tituloNaoEsperado + "' não deveria estar na lista de resultados.");
    }
}