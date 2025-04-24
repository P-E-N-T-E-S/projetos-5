package com.acervo.steps;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.ExemplarId;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn10;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReservaSteps {

    private Exemplar e1 = new Exemplar(new ExemplarId(123L), new Isbn10("9780136083221"), "Logo ali", null);
    Usuario u1 = new Usuario(new Matricula("123"), "Joao Leal Farias", "jfl@cesar.school", "1234", Cargo.GRADUANDO);

    @Given("o livro {string} esteja disponível para reserva no acervo da faculdade")
    public void livroDisponivelAcervo(String nome){
        Exemplar e1 = new Exemplar(new ExemplarId(123L), new Isbn10("10780136083221"), "Logo ali", null);
    }

    @When("eu solicitar a reserva do material {string}")
    public void solicitandoReserva(){
        e1.alugar(u1.getMatricula(), u1.getCargo());
    }

    @Then("o sistema deve registrar a minha reserva")
    public void sistemaRegistraReserva(){

    }

    @And("exibir que o livro está reservado, informando a data prevista para devolução")
    public void exibirLivroReservado(){
        assertNotNull(e1.getEmprestimo());
        assertEquals(LocalDate.now().plusDays(7), e1.getEmprestimo().getPeriodo().getFim());
    }
}
