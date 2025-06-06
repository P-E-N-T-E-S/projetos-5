Feature:Filtrar obras por tema

  Background: Usuário autenticado e obras cadastradas
    Given que eu seja um usuário autenticada no sistema
    And que as seguintes obras existam no catálogo:
      | titulo                  | tema                | ano_publicacao |
      | Duna                    | Ficção Científica   | 1965           |
      | Neuromancer             | Ficção Científica   | 1984           |
      | O Nome do Vento         | Fantasia            | 2007           |
      | A Revolução dos Bichos  | Sátira Política     | 1945           |

  Scenario: Filtrar obras por tema
    When eu selecionar o filtro "Tema" e escolher o valor "Ficção Científica"
    Then o sistema deve exibir as obras "Duna" e "Neuromancer"
    And o sistema não deve exibir a obra "O Nome do Vento"

  Scenario: Filtrar obras por título e data de postagem
    When eu utilizar o filtro "Título" com o valor "Revolução"
    And eu utilizar o filtro "Data de Postagem" com o intervalo de "1940" a "1950"
    Then o sistema deve exibir somente a obra "A Revolução dos Bichos"
    And o sistema não deve exibir a obra "Duna"