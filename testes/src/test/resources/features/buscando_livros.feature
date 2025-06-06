Feature: buscar o livro no acervo da Instituição
  Scenario: Buscar o local
    Given Que eu seja um usuário comum no sistema
    And O livro "Clean Code" esteja cadastrado no sistema com a localização de "Primeiro andar" "Prateleira 1 Cesar Brum"
    When Eu acessar o livro "Clean Code"
    Then O sistema deverá mostrar que ele se encontra no andar "Primeiro andar" e na localizacao "Prateleira 1 Cesar Brum"

  Scenario: Reportar sumiço
    Given Que eu seja um usuário comum no sistema
    And O livro "Código Sujo" esteja cadastrado no sistema com a localização de "Segundo andar" "Prateleira 1 Cesar Brum"
    When eu for no sistema e resgistrar que ele não se encontra no local indicado
    Then o sistema deve indicar que o livro está fora do local indicado
    And dispara um alerta a biblioteca

  Scenario: Devolvendo livro desaparecido
    Given eu seja um usuário bibliotecário no sistema
    And o livro "Código Sujo" esteja cadastrado no sistema como sumido
    When eu reportar que o livro foi encontrado e devolvido a seu lugar
    Then o aviso do sumiço do livro deverá desaparecer
    And o alerta da bibliotecario também