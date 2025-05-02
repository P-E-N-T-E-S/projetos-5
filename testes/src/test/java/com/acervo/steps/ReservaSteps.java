package com.acervo.steps;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.administracao.emprestimo.*;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.capibyte.acervo.dominio.core.compartilhado.DataUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ReservaSteps {

    private Exemplar e1 = new Exemplar(new CodigoDaObra(123L), new Isbn10("0132350882"), "Tá lá", null);
    private Exemplar e2 = new Exemplar(new CodigoDaObra(124L), new Isbn10("0132350882"), "Logo ali", null);
    private Exemplar e3 = new Exemplar(new CodigoDaObra(125L), new Isbn10("0132350882"), "Aqui pertinho", new Emprestimo(new Periodo(LocalDate.now(), DataUtil.adicionarDiasUteis(LocalDate.now(), 5)), new Matricula("11111")));
    private Usuario u1 = new Usuario(new Matricula("123"), "Joao Leal Farias", "jfl@cesar.school", "1234", Cargo.GRADUANDO);
    private Usuario u2 = new Usuario(new Matricula("124"), "Joao Leite Feldspato", "jfl@cesar.school", "1234", Cargo.BIBLIOTECARIA);
    private List<CodigoDaObra> exemplares = new ArrayList<>();
    Solicitacao solicitacao = new Solicitacao(u2.getMatricula(), LocalDate.now(), exemplares, Cargo.GRADUANDO);
    @Given("o livro Clean Code esteja disponível para reserva no acervo da faculdade")
    public void livroDisponivelAcervo(){
        assertNull(e1.getEmprestimo());
    }

    @When("eu solicitar a reserva do material Clean Code")
    public void solicitandoReserva(){
        e1.alugar(u1.getMatricula(), u1.getCargo());
    }

    @Then("o sistema deve registrar a minha reserva")
    public void sistemaRegistraReserva(){
        assertInstanceOf(Emprestimo.class, e1.getEmprestimo());
    }

    @And("exibir que o livro está reservado, informando a data prevista para devolução")
    public void exibirReservaRealizada(){
        assertNotNull(e1.getEmprestimo());
        assertEquals(DataUtil.adicionarDiasUteis(LocalDate.now(), 7), e1.getEmprestimo().getPeriodo().getFim());
    }

    @Given("que eu seja um usuário bibliotecário no sistema")
    public void usuArioBibliotecario(){
        assertEquals(u2.getCargo(), Cargo.BIBLIOTECARIA);
    }

    @And("exista uma solicitação pendente para o livro Clean Architecture")
    public void reservaPendenteParaLivro(){
        exemplares.add(e2.getExemplarId());
    }

    @When("eu aprovar a solicitação do material Clean Architecture")
    public void aprovarSolicitacao(){
        e2.alugar(u2.getMatricula(), u1.getCargo());
        assertTrue(solicitacao.getExemplares().isEmpty());
    }

    @Then("o sistema deve realizar o emprestimo")
    public void realizarEmprestimoPosSolicitacao(){
        assertNotNull(e2.getEmprestimo());
    }

    @And("confirmar a data prevista para a devolução do material")
    public void notificarClienteDataEntrega(){
        assertEquals(DataUtil.adicionarDiasUteis(LocalDate.now(), 7),e2.getEmprestimo().getPeriodo().getFim());
    }

    @Given("que o livro Bad Code esteja registrado como reservado no sistema")
    public void livroRegistradoReservado(){
        assertInstanceOf(Exemplar.class ,e3);
        assertNotNull(e3.getEmprestimo());
    }

    @When("um usuário tenta fazer a reserva do livro Bad Code")
    public void usuarioAcessaDados(){

    }

    @Then("o sistema deve exibir que o livro está reservado")
    public void exibirLivroReservado(){
        assertEquals("Livro Reservado", e3.isAlugado());
    }

    @And("mostrar a data prevista para a devolução do material")
    public void mostrarDataDevolucaoPrevista(){
        assertEquals(DataUtil.adicionarDiasUteis(LocalDate.now(), 5), e3.getEmprestimo().getPeriodo().getFim());
    }
}
