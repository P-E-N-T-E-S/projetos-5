Feature: realizando a indicação de livros
  Scenario: Indicar materia para outros usuários
    Given que eu seja um usuario comum no sistema
    And o livro "Clean Code" esteja cadastrado no sistema
    When eu indicar o material "Clean Code" para outros usuários
    Then o sistema deve exibir que o material foi indicado por mim
    And os outros usuarios devem visualizar essa indicação em suas telas de navegação ou seção de recomendações

  Scenario: Gerar link de compartilhamento para um material
    Given Que eu seja um usuario comum no sistema
    And o livro "Código Sujo" esteja cadastrado no sistema
    When eu solicitar a geração de um link de compartilhamento
    Then o sistema deve gerar um link unico de compartilhamento
    And o sistema deve exibir a opção de copiar o link para que eu possa compartilha-lo

  Scenario: Direcionar usuário diretamente ao material via link de compartilhamento
    Given que um usuario recebeu o link de compartilhamento gerado para o material "Código Sujo"
    When esse usuario clicar no link
    Then o sistema deve direcioná-lo diretamente para página do material "Códgio Sujo"
    And o usuario deve conseguir visuailzar todas as informações relevantes do material
