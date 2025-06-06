package com.acervo.steps;
import com.acervo.persistencia.memoria.FuncionalidadesSistema;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Exemplar;
import com.capibyte.acervo.dominio.core.acervo.exemplar.CodigoDaObra;
import com.capibyte.acervo.dominio.core.acervo.exemplar.Localizacao;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
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
import io.cucumber.java.Before;


public class ReservaSteps extends FuncionalidadesSistema {

    List<AutorId> autores;
    List<String> temas;
    List<CodigoDaObra> lista = new ArrayList<>();

    Livro livro = new Livro(new Isbn("1"), "Código Sujo", autores, "Sinopse do livro que legal uau", "1", 2021, 12, temas);
    Exemplar e1 = new Exemplar(new CodigoDaObra(1L), new Isbn("1"), new Localizacao("Primeiro andar", "prateleira 2"));
    Exemplar e2 = new Exemplar(new CodigoDaObra(2L), new Isbn("1"), new Localizacao("Segundo andar", "prateleira 1"));
    Exemplar e3 = new Exemplar(new CodigoDaObra(3L), new Isbn("1"), new Localizacao("Terceiro andar", "prateleira 3"));

    private Usuario u1 = new Usuario(new Matricula("123"), "Joao Leal Farias", "jfl@cesar.school", "1234", Cargo.GRADUANDO);
    private Usuario u2 = new Usuario(new Matricula("124"), "Joao Leite Feldspato", "jfl@cesar.school", "1234", Cargo.BIBLIOTECARIA);

    @Before
    public void setUp() {
        usuarioService.salvar(u1);
        exemplarService.salvar(e1);
        exemplarService.salvar(e2);
        exemplarService.salvar(e3);
    }

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
        usuarioService.salvar(u2);
    }

    @And("exista uma solicitação pendente para o livro Clean Architecture")
    public void reservaPendenteParaLivro(){
        this.lista.add(e2.getCodigoDaObra());
        solicitacaoService.salvarSolicitacao(new Solicitacao(new SolicitacaoId(1L), new Matricula("123"), LocalDate.now(), lista));
    }

    @When("eu aprovar a solicitação do material Clean Architecture")
    public void aprovarSolicitacao(){
        emprestimoService.aprovarEmprestimo(1L);

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
        e3.alugar(u1.getMatricula(), u1.getCargo());
        assertNotNull(e3.getEmprestimo());
    }

    @When("um usuário tenta fazer a reserva do livro Bad Code")
    public void usuarioAcessaDados(){
        // e3.alugar(u1.getMatricula(), u1.getCargo());
    }

    @Then("o sistema deve exibir que o livro está reservado")
    public void exibirLivroReservado(){
        assertEquals("Livro Reservado", e3.isAlugado());
    }

    @And("mostrar a data prevista para a devolução do material")
    public void mostrarDataDevolucaoPrevista(){
        assertEquals(DataUtil.adicionarDiasUteis(LocalDate.now(), 7), e3.getEmprestimo().getPeriodo().getFim());
    }
}
