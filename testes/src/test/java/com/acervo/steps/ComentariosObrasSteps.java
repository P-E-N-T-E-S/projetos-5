//package com.acervo.steps;
//
//import com.capibyte.acervo.dominio.core.acervo.autor.AutorId;
//import com.capibyte.acervo.dominio.core.acervo.livro.*;
//import com.capibyte.acervo.dominio.core.administracao.usuario.*;
//import com.capibyte.acervo.dominio.core.administracao.usuario.enums.Cargo;
//import com.capibyte.acervo.dominio.core.opiniao.*;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ComentariosObrasSteps {
//    private ComentarioService comentarioService;
//    private Livro livro;
//    private String comentarioEnviado;
//    private List<Comentario> comentariosListados;
//    private Exception exceptionCapturada;
//
//    @Before
//    public void setup() {
//        comentarioService = new ComentarioService(new ComentarioRepository() {
//            private final List<Comentario> comentarios = new ArrayList<>();
//
//            @Override
//            public void salvar(Comentario comentario) {
//                comentarios.add(comentario);
//            }
//
//            @Override
//            public List<Comentario> listarPorIsbn(String isbn) {
//                return comentarios.stream()
//                        .filter(c -> c.getIsbn().getIsbn().getCodigo().equals(isbn))
//                        .sorted(Comparator.comparing(c -> c.getUsuario().getCargo() != Cargo.PROFESSOR))
//                        .collect(Collectors.toList());
//            }
//        });
//
//        livro = new Livro(new Isbn10("0201633612"), "Clean Code", new AutorId(1), "");
//    }
//
//    @Given("que existe o livro registrado no sistema")
//    public void queExisteOLivroRegistradoNoSistema() {
//        assertInstanceOf(Livro.class, livro);
//    }
//
//    @When("eu enviar o comentário {string} na obra")
//    public void euEnviarOComentarioNaObra(String comentario) throws Exception {
//        this.comentarioEnviado = comentario;
//        Usuario u1 = new Usuario(new Matricula("123"), "Joao Leal Farias", "jfl@cesar.school", "1234", Cargo.GRADUANDO);
//        comentarioService.adicionarComentario(livro.getIsbn().getCodigo(), livro, u1, comentario);
//    }
//
//    @Then("o sistema deve registrar o meu comentario")
//    public void oSistemaDeveRegistrarOComentario() {
//        List<Comentario> comentarios = comentarioService.listarComentarios(livro.getIsbn().getCodigo());
//        assertFalse(comentarios.isEmpty(), "Nenhum comentário foi registrado");
//        boolean encontrado = comentarios.stream()
//                .anyMatch(c -> c.getConteudo().equals(comentarioEnviado));
//        assertTrue(encontrado, "Comentário não foi encontrado: " + comentarioEnviado);
//    }
//
//
//    @Given("que existam comentários cadastrados na obra por diversos usuários")
//    public void queExistamComentáriosCadastradosNaObraPorDiversosUsuários() throws Exception {
//        Usuario professor = new Usuario(new Matricula("PROF123"), "Maria Silva", "maria@cesar.school", "senha", Cargo.PROFESSOR);
//        Usuario aluno = new Usuario(new Matricula("ALUNO456"), "João Souza", "joao@cesar.school", "senha", Cargo.GRADUANDO);
//        Usuario tecnico = new Usuario(new Matricula("TEC789"), "Carlos Lima", "carlos@cesar.school", "senha", Cargo.MESTRADO);
//
//        comentarioService.adicionarComentario(
//                livro.getIsbn().getCodigo(),
//                livro,
//                professor,
//                "Excelente abordagem teórica!"
//        );
//
//        comentarioService.adicionarComentario(
//                livro.getIsbn().getCodigo(),
//                livro,
//                aluno,
//                "Achei um pouco complexo no capítulo 3"
//        );
//
//        comentarioService.adicionarComentario(
//                livro.getIsbn().getCodigo(),
//                livro,
//                tecnico,
//                "Bom para consulta rápida"
//        );
//    }
//
//    @When("qualquer usuário acessar os comentarios da obra")
//    public void qualquerUsuárioAcessarOsComentariosDaObra() {
//        comentariosListados = comentarioService.listarComentarios(livro.getIsbn().getCodigo());
//    }
//
//    @Then("o sistema deve exibir todos os comentários associados à obra")
//    public void oSistemaDeveExibirTodosOsComentáriosAssociadosÀObra() {
//        assertEquals(3, comentariosListados.size(), "Deveriam existir 3 comentários");
//    }
//
//    @And("Destacar os comentários de professores")
//    public void destacarOsComentáriosDeProfessores() {
//        List<Comentario> comentariosProfessores = comentariosListados.stream()
//                .filter(c -> c.getUsuario().getCargo() == Cargo.PROFESSOR)
//                .toList();
//
//        assertFalse(comentariosProfessores.isEmpty(), "Deveria haver comentários de professores");
//
//        Comentario primeiroComentario = comentariosListados.getFirst();
//        assertEquals(Cargo.PROFESSOR, primeiroComentario.getUsuario().getCargo(),
//                "Comentário de professor deveria estar no topo");
//    }
//
//    @Given("que existe um livro registrado no sistema")
//    public void queExisteUmLivroRegistradoNoSistema() {
//        assertInstanceOf(Livro.class, livro);
//    }
//
//    @When("eu tentar enviar um comentário vazio na página da obra")
//    public void euTentarEnviarUmComentárioVazioNaPáginaDaObra() {
//        try {
//            Usuario u1 = new Usuario(new Matricula("123"), "Joao Leal Farias", "jfl@cesar.school", "1234", Cargo.GRADUANDO);
//            comentarioService.adicionarComentario(livro.getIsbn().getCodigo(), livro, u1, "");
//        } catch (Exception e) {
//            exceptionCapturada = e;
//        }
//    }
//
//    @Then("o sistema não deve registrar o comentário")
//    public void oSistemaNãoDeveRegistrarOComentário() {
//        List<Comentario> comentarios = comentarioService.listarComentarios(livro.getIsbn().getCodigo());
//        boolean comentarioVazioExiste = comentarios.stream()
//                .anyMatch(c -> c.getConteudo().isEmpty());
//
//        assertFalse(comentarioVazioExiste, "Comentário vazio foi registrado");
//    }
//
//    @And("deve exibir uma mensagem de erro informando que o comentário não pode ser vazio")
//    public void deveExibirUmaMensagemDeErroInformandoQueOComentárioNãoPodeSerVazio() {
//        assertNotNull(exceptionCapturada, "Nenhuma exceção foi lançada");
//        assertEquals("Conteúdo do comentário não pode ser vazio", exceptionCapturada.getMessage());
//    }
//}
