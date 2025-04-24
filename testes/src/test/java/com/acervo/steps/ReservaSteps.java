package com.acervo.steps;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReservaSteps {
    @Given("que eu seja um usuário comum no sistema")
    public void dadoUsuarioComum(){
        Usuario u = new Usuario(new Matricula("123"), "Joao Leal Farias", "jfl@cesar.school", "1234", Cargo.GRADUANDO);
    }

    @And("o livro {string} esteja disponível para reserva no acervo da faculdade")
    public void livroDisponivelAcervo(){

    }

    @When("eu solicitar a reserva do material {string}")
    public void solicitandoReserva(){

    }

    @Then("o sistema deve registrar a minha reserva")
    public void sistemaRegistraReserva(){

    }

    @And("exibir que o livro está reservado, informando a data prevista para devolução")
    public void exibirLivroReservado(){

    }
}
