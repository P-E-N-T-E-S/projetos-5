package com.acervo.steps;

import com.acervo.persistencia.memoria.FuncionalidadesSistema;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Localizacao;
import com.capibyte.acervo.dominio.core.acervo.exemplar.enums.Status;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.acervo.livro.LivroService;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

public class BuscaLivrosSteps extends FuncionalidadesSistema {

    List<AutorId> autores;
    List<String> temas;
    List<Livro> livros;
    Exemplar e;

    @Given("Que eu seja um usuário comum no sistema")
    public void usuarioComumNoSistema() {
        Usuario usuario = new Usuario(new Matricula("1"), "Andre", "Andre@gmail.com", "123", Cargo.GRADUANDO);
        usuarioService.salvar(usuario);
    }

    @And("O livro {string} esteja cadastrado no sistema com a localização de {string} {string}")
    public void livroCadastradoNoSistemaCesarBrum(String nome, String andar, String localizacao) {
        Livro livro = new Livro(new Isbn("1"), nome, autores, "Sinopse do livro que legal uau", "1", 2021, 12, temas);
        Exemplar e = new Exemplar(new CodigoDaObra(1L), new Isbn("1"), new Localizacao(andar, localizacao));
        livroService.salvar(livro);
        exemplarService.salvar(e);
    }

    @When("Eu acessar o livro {string}")
    public void acessarOLivro(String nome_livro) {
        livros = livroService.buscarPorTitulo(nome_livro);
        assertFalse(livros.isEmpty());
    }

    @Then("O sistema deverá mostrar que ele se encontra no andar {string} e na localizacao {string}")
    public void sistemaRetornaLocalizacaoBrum(String andar, String localizacao) {
        Exemplar e = exemplarService.buscarPorId(new CodigoDaObra(1L));
        assertEquals(e.getLocalizacao().getAndar(), andar);
        assertEquals(e.getLocalizacao().getPrateleira(), localizacao);
    }

    @When("eu for no sistema e resgistrar que ele não se encontra no local indicado")
    public void euForNoSistemaResgistrarNoLocalIndicado() {

    }

    @Then("o sistema deve indicar que o livro está fora do local indicado")
    public void oSistemaDeIndicarLocalIndicado() {

    }

    @And("dispara um alerta a biblioteca")
    public void disparaUmAlertaABiblioteca() {

    }

    @Given("eu seja um usuário bibliotecário no sistema")
    public void euSejaUmBibliotecarioNoSistema() {
        Usuario usuario = new Usuario(new Matricula("2"), "Dona Biblioteca", "biblioteca@gmail.com", "123", Cargo.BIBLIOTECARIA);
        usuarioService.salvar(usuario);
    }

    @And("o livro {string} esteja cadastrado no sistema como sumido")
    public void livroCadastradoSumido(String nome) {
        Livro livro = new Livro(new Isbn("1"), nome, autores, "Sinopse do livro que legal uau", "1", 2021, 12, temas);
        this.e = new Exemplar(new CodigoDaObra(1L), new Isbn("1"), new Localizacao("Primeiro andar", "Prateleira 2"));
        e.setStatus(Status.INDISPONIVEL);
        livroService.salvar(livro);
        exemplarService.salvar(e);
    }

    @When("eu reportar que o livro foi encontrado e devolvido a seu lugar")
    public void reportandoLivroEncontrado(){
        // Simulando reportar que o livro foi encontrado
        e.setStatus(Status.DISPONIVEL);
    }

    @Then("o aviso do sumiço do livro deverá desaparecer")
    public void avisodeSumicoDesaparece(){

    }

    @And("o alerta da bibliotecario também")
    public void alertaBibliotecarioSome(){

    }
}
