package com.acervo.steps;

import com.acervo.persistencia.memoria.FuncionalidadesSistema;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.obra.DOI;
import com.capibyte.acervo.dominio.core.acervo.obra.Obra;
import com.capibyte.acervo.dominio.core.acervo.obra.PalavraChave;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RevisaoSteps extends FuncionalidadesSistema {

    List<AutorId> autores;
    List<PalavraChave> palavraChaves;


    @Given("que eu seja uma usuaria bibliotecária no sistema")
    public void que_eu_seja_uma_usuaria_bibliotecaria_no_sistema() {
        Usuario u2 = new Usuario(new Matricula("124"), "Joao Leite Feldspato", "jfl@cesar.school", "1234", Cargo.BIBLIOTECARIA);
        usuarioService.salvar(u2);
    }

    @Given("existam trabalhos academicos recém adicionados pendentes de revisao")
    public void existam_trabalhos_academicos_recem_adicionados_pendentes_de_revisao() {
        Obra o1 = new Obra(new DOI("123"), "Um brilho no céu! Ovnis?", autores, palavraChaves, "Um ovni caiu no brasil e foi analisado", LocalDate.now(), "AAAAAAAAAAAAAAAAAAA", null, false);
        Obra o2 = new Obra(new DOI("456"), "Um brilho no céu! Ovnis?", autores, palavraChaves, "Um ovni caiu no brasil e foi analisado", LocalDate.now(), "AAAAAAAAAAAAAAAAAAA", null, true);
        Obra o3 = new Obra(new DOI("789"), "Um brilho no céu! Ovnis?", autores, palavraChaves, "Um ovni caiu no brasil e foi analisado", LocalDate.now(), "AAAAAAAAAAAAAAAAAAA", null, false);
        obraService.salvar(o1);
        obraService.salvar(o2);
        obraService.salvar(o3);
    }

    @When("eu acessar a área de revisão de trabalhos")
    public void eu_acessar_a_area_de_revisao_de_trabalhos() {
        obraService.buscarPorValidado(false);
    }

    @Then("o sistema deve exibir todos os trabalhos academicos recém adicionados")
    public void o_sistema_deve_exibir_todos_os_trabalhos_academicos_recem_adicionados() {
        List<Obra> obras_buscar = new ArrayList<Obra>();
        obras_buscar = obraService.buscarPorValidado(false);

        assertEquals(obras_buscar.size(), 2);
    }

    @And("cada trabalho deve indicar que necessita de {string}")
    public void cada_trabalho_deve_indicar_que_necessita_de(String necessidade) {
        List<Obra> obras_buscar = new ArrayList<Obra>();
        obras_buscar = obraService.buscarPorValidado(false);

        assertEquals(obras_buscar.get(0).isValidado(), false );
        assertEquals(obras_buscar.get(1).isValidado(), false );
    }

    @Given("QUe eu seja uma usuaria biblitoecaria no sistema")
    public void que_eu_seja_uma_usuaria_biblitoecaria_no_sistema() {
        Usuario u2 = new Usuario(new Matricula("124"), "Joao Leite Feldspato", "jfl@cesar.school", "1234", Cargo.BIBLIOTECARIA);
        usuarioService.salvar(u2);
    }

    @Given("o trabalho {string} esteja listado para revisao")
    public void o_trabalho_esteja_listado_para_revisao(String trabalho) {
        Obra o1 = new Obra(new DOI("123"), trabalho, autores, palavraChaves, "Um ovni caiu no brasil e foi analisado", LocalDate.now(), "AAAAAAAAAAAAAAAAAAA", null, false);
        Obra o2 = new Obra(new DOI("456"), "Um brilho no céu! Ovnis?", autores, palavraChaves, "Um ovni caiu no brasil e foi analisado", LocalDate.now(), "AAAAAAAAAAAAAAAAAAA", null, true);
        Obra o3 = new Obra(new DOI("789"), "Um brilho no céu! Ovnis?", autores, palavraChaves, "Um ovni caiu no brasil e foi analisado", LocalDate.now(), "AAAAAAAAAAAAAAAAAAA", null, false);
        obraService.salvar(o1);
        obraService.salvar(o2);
        obraService.salvar(o3);

        assertEquals(obraService.buscarPorTitulo(trabalho).getFirst().getTitulo(), trabalho);

    }

    @When("eu verificar que o trabalho {string} possui coerencia textual")
    public void eu_verificar_que_o_trabalho_possui_coerencia_textual(String trabalho) {
    }

    @And("confirmar que está conforme as normas ABNT")
    public void confirmar_que_esta_conforme_as_normas_abnt() {
        List<Obra> obras_buscadas = obraService.buscarPorTitulo("Trabalho n°1001");
        obraService.validar(obras_buscadas.getFirst().getDoi());
    }

    @Then("eu devo aprovar o trabalho {string}")
    public void eu_devo_aprovar_o_trabalho(String trabalho) {
        List<Obra> obras_buscadas = obraService.buscarPorTitulo(trabalho);
        assertEquals(obras_buscadas.getFirst().isValidado(), true);
    }

    @When("eu identificar que o trabalho {string} possui incoerências ou não atende as normas ABNT")
    public void eu_identificar_que_o_trabalho_possui_incoerencias_ou_nao_atende_as_normas_abnt(String trabalho) {
        assertEquals(obraService.buscarPorTitulo(trabalho).getFirst().isValidado(), false);
    }

    @Then("eu devo rejeitar o trabalho {string}")
    public void eu_devo_rejeitar_o_trabalho(String trabalho) {
        obraService.buscarPorTitulo(trabalho).getFirst().setValidado(false);
    }

    @And("o sistema deve marcar o trabalo como {string} ou {string}")
    public void o_sistema_deve_marcar_o_trabalo_como_ou(String status1, String status2) {

    }

    @And("solicitar que o autor realize as devidas correções para que o trabalho se torne conforme para divulgação")
    public void solicitar_que_o_autor_realize_as_devidas_correcoes_para_que_o_trabalho_se_torne_conforme_para_divulgacao() {

    }
}
