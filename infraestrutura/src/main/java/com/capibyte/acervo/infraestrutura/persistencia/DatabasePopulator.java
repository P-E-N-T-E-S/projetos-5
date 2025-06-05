package com.capibyte.acervo.infraestrutura.persistencia;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro.LivroJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra.ObraJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar.ExemplarJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario.UsuarioJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.opiniao.ComentarioJPA;
import com.capibyte.acervo.dominio.core.acervo.exemplar.enums.Status;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.EmprestimoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo.PeriodoJPA;
import com.capibyte.acervo.infraestrutura.persistencia.core.log.PesquisaLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@Component
public class DatabasePopulator {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void popularBancoDeDados() {
        Random random = new Random();
        
        // Criar autores
        AutorJPA[] autores = new AutorJPA[15];
        String[] nomesAutores = {
            "Machado de Assis", "Clarice Lispector", "Jorge Amado", "Graciliano Ramos",
            "Guimarães Rosa", "Eça de Queirós", "William Shakespeare", "Fiódor Dostoiévski",
            "Gabriel García Márquez", "Jorge Luis Borges", "Virginia Woolf", "Franz Kafka",
            "Albert Camus", "George Orwell", "J.R.R. Tolkien"
        };

        for (int i = 0; i < autores.length; i++) {
            autores[i] = new AutorJPA();
            autores[i].setNome(nomesAutores[i]);
            entityManager.persist(autores[i]);
        }

        // Criar obras
        ObraJPA[] obras = new ObraJPA[15];
        String[] titulosObras = {
            "Dom Casmurro", "A Hora da Estrela", "Capitães da Areia", "Vidas Secas",
            "Grande Sertão: Veredas", "Os Maias", "Hamlet", "Crime e Castigo",
            "Cem Anos de Solidão", "Ficções", "Mrs. Dalloway", "A Metamorfose",
            "O Estrangeiro", "1984", "O Senhor dos Anéis"
        };

        String[] resumosObras = {
            "Romance que narra a história de Bentinho e Capitu.",
            "Narra a história de Macabéa, uma jovem nordestina no Rio de Janeiro.",
            "História de um grupo de meninos de rua em Salvador.",
            "Retrata a vida de uma família de retirantes no sertão nordestino.",
            "Narra a história de Riobaldo e seu pacto com o diabo.",
            "Crítica à sociedade portuguesa do século XIX.",
            "Tragédia sobre vingança e loucura.",
            "Exploração da psicologia do crime e da culpa.",
            "Saga da família Buendía em Macondo.",
            "Coletânea de contos fantásticos.",
            "Um dia na vida de Clarissa Dalloway.",
            "A transformação de Gregor Samsa em inseto.",
            "Reflexão sobre o absurdo da existência.",
            "Distopia sobre vigilância e controle social.",
            "A jornada do anel para destruir o mal."
        };

        // Array de datas de publicação com algumas repetidas
        LocalDate[] datasPublicacao = new LocalDate[15];
        for (int i = 0; i < 15; i++) {
            if (i < 5) {
                // Primeiras 5 obras com datas únicas
                datasPublicacao[i] = LocalDate.of(1800 + i * 10, 1, 1);
            } else {
                // Últimas 10 obras com algumas datas repetidas
                int anoBase = 1800 + (i % 3) * 20; // 3 grupos de anos
                int mes = 1 + (i % 12); // Meses variados
                int dia = 1 + (i % 28); // Dias variados
                datasPublicacao[i] = LocalDate.of(anoBase, mes, dia);
            }
        }

        for (int i = 0; i < obras.length; i++) {
            obras[i] = new ObraJPA();
            obras[i].setDoi("10.1234/" + (i + 1));
            obras[i].setTitulo(titulosObras[i]);
            obras[i].setAutoresObra(Arrays.asList(autores[i]));
            obras[i].setDataPublicacao(datasPublicacao[i]);
            obras[i].setResumo(resumosObras[i]);
            
            // Variação nas palavras-chave
            List<String> palavrasChave = new ArrayList<>();
            palavrasChave.add("literatura");
            if (i % 3 == 0) palavrasChave.add("romance");
            if (i % 4 == 0) palavrasChave.add("clássico");
            if (i % 5 == 0) palavrasChave.add("brasileira");
            if (i % 6 == 0) palavrasChave.add("estrangeira");
            palavrasChave.add("literatura"); // Temporariamente usando "literatura" como tema padrão
            
            obras[i].setPalavrasChave(palavrasChave);
            obras[i].setValidado(true);
            entityManager.persist(obras[i]);
        }

        // Criar livros
        LivroJPA[] livros = new LivroJPA[15];
        String[] isbns = {
            "9788535902778", "9788535909555", "9788535914849", "9788535914856",
            "9788535914863", "9788535914870", "9788535914887", "9788535914894",
            "9788535914900", "9788535914917", "9788535914924", "9788535914931",
            "9788535914948", "9788535914955", "9788535914962"
        };

        for (int i = 0; i < livros.length; i++) {
            livros[i] = new LivroJPA();
            livros[i].setIsbn(isbns[i]);
            livros[i].setTitulo(titulosObras[i]);
            livros[i].setAutores(Arrays.asList(autores[i]));
            livros[i].setAnoDePublicacao(1800 + i * 10);
            livros[i].setSinopse(resumosObras[i]);
            livros[i].setNumeroChamada("FIC " + nomesAutores[i].substring(0, 4).toUpperCase());
            livros[i].setQuantidadeDePaginas(200 + i * 20);
            livros[i].setTemas(Arrays.asList("literatura", "romance", "clássico"));
            entityManager.persist(livros[i]);
        }

        // Criar usuários
        UsuarioJPA[] usuarios = new UsuarioJPA[15];
        String[] nomesUsuarios = {
            "João Silva", "Maria Santos", "Pedro Oliveira", "Ana Costa",
            "Carlos Souza", "Lucia Ferreira", "Roberto Almeida", "Juliana Lima",
            "Fernando Pereira", "Patricia Rodrigues", "Ricardo Santos", "Beatriz Martins",
            "Marcelo Oliveira", "Camila Silva", "Lucas Costa"
        };

        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = new UsuarioJPA();
            usuarios[i].setMatricula("2024" + String.format("%03d", i + 1));
            usuarios[i].setNome(nomesUsuarios[i]);
            usuarios[i].setEmail(nomesUsuarios[i].toLowerCase().replace(" ", ".") + "@email.com");
            usuarios[i].setSenha("senha" + (i + 1));
            usuarios[i].setCargo(i < 3 ? 2L : 1L); // 3 bibliotecários, resto leitores
            entityManager.persist(usuarios[i]);
        }

        // Criar exemplares (3 por livro) com empréstimos
        ExemplarJPA[] exemplares = new ExemplarJPA[45];
        for (int i = 0; i < livros.length; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                exemplares[index] = new ExemplarJPA();
                exemplares[index].setCodigoDaObra((long) (index + 1));
                exemplares[index].setLivro(livros[i]);
                exemplares[index].setValor(100.0 + (i * 10.0));

                if (j == 2) { // Exemplar emprestado
                    EmprestimoJPA emprestimo = new EmprestimoJPA();
                    PeriodoJPA periodo = new PeriodoJPA();
                    
                    // Variação nos períodos de empréstimo
                    int diasAtras = random.nextInt(15); // Empréstimos iniciados nos últimos 15 dias
                    int duracaoEmprestimo = 7 + random.nextInt(14); // Duração entre 7 e 21 dias
                    
                    periodo.setInicio(LocalDate.now().minusDays(diasAtras));
                    periodo.setFim(LocalDate.now().minusDays(diasAtras).plusDays(duracaoEmprestimo));
                    
                    emprestimo.setTomador(usuarios[i % 12]); // Usa os leitores (índices 3-14)
                    emprestimo.setPeriodo(periodo);
                    exemplares[index].setEmprestimo(emprestimo);
                    exemplares[index].setStatus(Status.EMPRESTADO);
                } else if (j == 1) {
                    exemplares[index].setStatus(Status.INDISPONIVEL);
                } else {
                    exemplares[index].setStatus(Status.DISPONIVEL);
                }

                entityManager.persist(exemplares[index]);
            }
        }

        // Criar comentários (2 por livro)
        ComentarioJPA[] comentarios = new ComentarioJPA[30];
        String[] conteudosComentarios = {
            "Excelente obra! Uma das melhores que já li.",
            "Uma obra profunda e reflexiva.",
            "Clássico da literatura que merece ser lido.",
            "Narrativa envolvente e personagens marcantes.",
            "Uma história que te prende do início ao fim.",
            "Obra fundamental para entender a literatura.",
            "Personagens complexos e bem desenvolvidos.",
            "Uma jornada literária inesquecível.",
            "Narrativa rica em detalhes e significados.",
            "Um livro que muda sua perspectiva.",
            "Obra-prima da literatura mundial.",
            "História cativante e bem construída.",
            "Personagens memoráveis e diálogos brilhantes.",
            "Uma experiência literária única.",
            "Livro que merece ser relido várias vezes."
        };

        for (int i = 0; i < livros.length; i++) {
            for (int j = 0; j < 2; j++) {
                int index = i * 2 + j;
                comentarios[index] = new ComentarioJPA();
                comentarios[index].setId(index + 1);
                comentarios[index].setLivro(livros[i]);
                comentarios[index].setUsuario(usuarios[j]);
                comentarios[index].setConteudo(conteudosComentarios[i]);
                comentarios[index].setDataCriacao(LocalDateTime.now().minusDays(index));
                entityManager.persist(comentarios[index]);
            }
        }

        // Criar logs de pesquisa
        String[] temasPesquisa = {
            "literatura", "romance", "clássico", "ficção", "drama",
            "poesia", "biografia", "história", "filosofia", "ciência",
            "tecnologia", "arte", "música", "cinema", "teatro",
            "fantasia", "mistério", "suspense", "terror", "aventura",
            "infantil", "juvenil", "didático", "acadêmico", "pesquisa"
        };

        LocalDateTime dataBase = LocalDateTime.now().minusMonths(6); // Dados dos últimos 6 meses

        // Criar 1000 logs de pesquisa distribuídos nos últimos 6 meses
        for (int i = 0; i < 1000; i++) {
            PesquisaLog log = new PesquisaLog();
            
            // Distribuir os temas com pesos diferentes
            int temaIndex;
            double peso = random.nextDouble();
            if (peso < 0.3) { // 30% de chance para os temas mais populares
                temaIndex = random.nextInt(5); // Primeiros 5 temas
            } else if (peso < 0.6) { // 30% de chance para os temas medianos
                temaIndex = 5 + random.nextInt(10); // Próximos 10 temas
            } else { // 40% de chance para os temas menos populares
                temaIndex = 15 + random.nextInt(10); // Últimos 10 temas
            }
            
            log.setTema(temasPesquisa[temaIndex]);
            
            // Distribuir as datas com mais concentração nos últimos 3 meses
            int diasAtras = random.nextInt(180); // 180 dias = 6 meses
            if (diasAtras > 90) { // Se for mais de 3 meses atrás
                diasAtras = 90 + (diasAtras - 90) / 2; // Reduz a frequência
            }
            
            // Adicionar variação de horas para não ter todas as pesquisas no mesmo horário
            int horasAleatorias = random.nextInt(24);
            int minutosAleatorios = random.nextInt(60);
            
            log.setDataPesquisa(dataBase.plusDays(diasAtras)
                    .plusHours(horasAleatorias)
                    .plusMinutes(minutosAleatorios));
            
            entityManager.persist(log);
        }
    }
} 