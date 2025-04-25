Feature: Comentários em obras

  Scenario: Adicionar comentário a uma obra
    Given que existe o livro registrado no sistema
    When eu enviar o comentário "Muito interessante esta obra!" na obra
    Then o sistema deve registrar o meu comentario

  Scenario: Exibir todos os comentários da obra e Destacar o comentário de professores
    Given que existam comentários cadastrados na obra por diversos usuários
    When qualquer usuário acessar os comentarios da obra
    Then o sistema deve exibir todos os comentários associados à obra
    And Destacar os comentários de professores

  Scenario: Validação de comentário vazio
    Given que existe um livro registrado no sistema
    When eu tentar enviar um comentário vazio na página da obra
    Then o sistema não deve registrar o comentário
    And deve exibir uma mensagem de erro informando que o comentário não pode ser vazio
