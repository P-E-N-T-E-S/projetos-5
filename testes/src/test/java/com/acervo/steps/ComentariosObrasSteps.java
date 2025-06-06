package com.acervo.steps;

import com.acervo.persistencia.memoria.FuncionalidadesSistema;
import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
import com.capibyte.acervo.dominio.core.acervo.livro.Isbn;
import com.capibyte.acervo.dominio.core.acervo.livro.Livro;
import com.capibyte.acervo.dominio.core.administracao.usuario.Matricula;
import com.capibyte.acervo.dominio.core.administracao.usuario.Usuario;
import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
import com.capibyte.acervo.dominio.core.opiniao.Comentario;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComentariosObrasSteps extends FuncionalidadesSistema {

    private Livro livro;
    private Usuario usuarioAluno;
    private Usuario usuarioProfessor;
    private Usuario usuarioMestrado;

    private String comentarioEnviado;
    private List<Comentario> comentariosListados;
    private Exception exceptionCapturada;

    @Before
    public void setup() {
        List<AutorId> autores = new ArrayList<>();
        List<String> temas = new ArrayList<>();
        Isbn isbn = new Isbn("978-0132350884");

        this.livro = new Livro(isbn, "Clean Code", autores, "Um livro sobre boas práticas de codificação.", "1st", 2008, 464, temas);
        livroService.salvar(this.livro);

        this.usuarioAluno = new Usuario(new Matricula("ALUNO456"), "João Souza", "joao@cesar.school", "senha", Cargo.GRADUANDO);
        this.usuarioProfessor = new Usuario(new Matricula("PROF123"), "Maria Silva", "maria@cesar.school", "senha", Cargo.PROFESSOR);
        this.usuarioMestrado = new Usuario(new Matricula("TEC789"), "Carlos Lima", "carlos@cesar.school", "senha", Cargo.MESTRADO);
        usuarioService.salvar(usuarioAluno);
        usuarioService.salvar(usuarioProfessor);
        usuarioService.salvar(usuarioMestrado);

        this.exceptionCapturada = null;
    }

    @Given("que existe o livro registrado no sistema")
    public void queExisteOLivroRegistradoNoSistema() {
        assertNotNull(livroService.buscarPorIsbn(this.livro.getIsbn()), "O livro padrão não foi salvo corretamente no setup.");
    }

    @When("eu enviar o comentário {string} na obra")
    public void euEnviarOComentarioNaObra(String comentario) throws Exception {
        this.comentarioEnviado = comentario;
        Comentario novoComentario = new Comentario(
                livro.getIsbn(),
                comentario,
                LocalDateTime.now(),
                usuarioAluno.getMatricula()
        );
        comentarioService.adicionarComentario(novoComentario);
    }

    @Then("o sistema deve registrar o meu comentario")
    public void oSistemaDeveRegistrarOComentario() {
        List<Comentario> comentarios = comentarioService.listarComentarios(livro.getIsbn().getCodigo());
        assertFalse(comentarios.isEmpty(), "A lista de comentários não deveria estar vazia.");

        boolean encontrado = comentarios.stream()
                .anyMatch(c -> c.getConteudo().equals(comentarioEnviado));
        assertTrue(encontrado, "O comentário '" + comentarioEnviado + "' não foi encontrado na lista.");
    }

    @Given("que existam comentários cadastrados na obra por diversos usuários")
    public void queExistamComentariosCadastradosNaObraPorDiversosUsuarios() throws Exception {
        Comentario comentarioProf = new Comentario(livro.getIsbn(), "Excelente abordagem teórica!", LocalDateTime.now(), usuarioProfessor.getMatricula());
        comentarioService.adicionarComentario(comentarioProf);

        Comentario comentarioAluno = new Comentario(livro.getIsbn(), "Achei um pouco complexo no capítulo 3", LocalDateTime.now(), usuarioAluno.getMatricula());
        comentarioService.adicionarComentario(comentarioAluno);

        Comentario comentarioMestrado = new Comentario(livro.getIsbn(), "Bom para consulta rápida", LocalDateTime.now(), usuarioMestrado.getMatricula());
        comentarioService.adicionarComentario(comentarioMestrado);
    }

    @When("qualquer usuário acessar os comentarios da obra")
    public void qualquerUsuarioAcessarOsComentariosDaObra() {
        comentariosListados = comentarioService.listarComentarios(livro.getIsbn().getCodigo());
    }

    @Then("o sistema deve exibir todos os comentários associados à obra")
    public void oSistemaDeveExibirTodosOsComentariosAssociadosÀObra() {
        assertNotNull(comentariosListados, "A lista de comentários não deveria ser nula.");
        assertEquals(3, comentariosListados.size(), "Deveriam ser exibidos 3 comentários.");
    }

    @And("Destacar os comentários de professores")
    public void destacarOsComentariosDeProfessores() {
        assertFalse(comentariosListados.isEmpty(), "A lista de comentários está vazia, não é possível verificar a ordenação.");

        Comentario primeiroComentario = comentariosListados.get(0);

        Matricula matriculaDoAutor = primeiroComentario.getUsuario();
        assertNotNull(matriculaDoAutor, "O primeiro comentário não tem uma matrícula de autor associada.");

        Usuario autorDoComentario = usuarioService.buscarPorMatricula(matriculaDoAutor.getCodigo());
        assertNotNull(autorDoComentario, "O autor do comentário com matrícula '" + matriculaDoAutor.getCodigo() + "' não foi encontrado.");

        assertEquals(Cargo.PROFESSOR, autorDoComentario.getCargo(),
                "O comentário de professor deveria ser o primeiro da lista.");
    }

    @Given("que existe um livro registrado no sistema")
    public void queExisteOLivroRegistradoNoSistemaVazio() {
        assertNotNull(livroService.buscarPorIsbn(this.livro.getIsbn()), "O livro padrão não foi salvo corretamente no setup.");
    }

    @When("eu tentar enviar um comentário vazio na página da obra")
    public void euTentarEnviarUmComentarioVazioNaPaginaDaObra() {
        try {
            Comentario comentarioVazio = new Comentario(
                    livro.getIsbn(),
                    "",
                    LocalDateTime.now(),
                    usuarioAluno.getMatricula()
            );
            comentarioService.adicionarComentario(comentarioVazio);
        } catch (Exception e) {
            this.exceptionCapturada = e;
        }
    }

    @Then("o sistema não deve registrar o comentário")
    public void oSistemaNaoDeveRegistrarOComentario() {
        List<Comentario> comentarios = comentarioService.listarComentarios(livro.getIsbn().getCodigo());
        assertTrue(comentarios.isEmpty(), "Nenhum comentário deveria ter sido registrado para este livro.");
    }

    @And("deve exibir uma mensagem de erro informando que o comentário não pode ser vazio")
    public void deveExibirUmaMensagemDeErroInformandoQueOComentarioNaoPodeSerVazio() {
        assertNotNull(exceptionCapturada, "Uma exceção deveria ter sido lançada para comentário vazio.");
        assertEquals("Conteúdo do comentário não pode ser vazio", exceptionCapturada.getMessage());
    }
}