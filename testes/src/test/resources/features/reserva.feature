Feature: Gerenciamento de Reservas de Livros

  Scenario: Solicitar reserva de um livro disponível
    Given que eu seja um usuário comum no sistema
    And o livro "Clean Code" esteja disponível para reserva no acervo da faculdade
    When eu solicitar a reserva do material "Clean Code"
    Then o sistema deve registrar a minha reserva
    And exibir que o livro está reservado, informando a data prevista para devolução

  Scenario: Aprovação da reserva pelo bibliotecário
    Given que eu seja um usuário bibliotecário no sistema
    And exista uma reserva pendente para o livro "Clean Architecture"
    When eu aprovar a reserva do material "Clean Architecture"
    Then o sistema deve atualizar o status da reserva para "aprovado"
    And notificar o usuário que sua reserva foi aprovada
    And confirmar a data prevista para a devolução do material

  Scenario: Consultar status de reserva e data prevista para devolução
    Given que o livro "PLACEHOLDER" esteja registrado como reservado no sistema
    When um usuário acessar os detalhes do livro "Bad Code"
    Then o sistema deve exibir que o livro está reservado
    And mostrar a data prevista para a devolução do material
