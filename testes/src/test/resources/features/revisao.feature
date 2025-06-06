Feature: Revisar livros e trabalhos no sistema
  Scenario: Acessar a lista de trabalhos pendentes para revisão
    Given que eu seja uma usuaria bibliotecária no sistema
    And existam trabalhos academicos recém adicionados pendentes de revisao
    When eu acessar a área de revisão de trabalhos
    Then o sistema deve exibir todos os trabalhos academicos recém adicionados
    And cada trabalho deve indicar que necessita de "verificação de coerencia e conformidade com a norma ABNT"

  Scenario: Aprovar trabalho academico que cumpre normas
    Given QUe eu seja uma usuaria biblitoecaria no sistema
    And o trabalho "Trabalho n°1001" esteja listado para revisao
    When eu verificar que o trabalho "Trabalho n°1001" possui coerencia textual
    And confirmar que está conforme as normas ABNT
    Then eu devo aprovar o trabalho "Trabalho n°1001"

  Scenario: Rejeitar trabalho acadêmico que não cumpre as normas
    Given que eu seja uma usuaria bibliotecária no sistema
    And o trabalho "Trabalho n° 1002" esteja listado para revisao
    When eu identificar que o trabalho "Trabalho n° 1002" possui incoerências ou não atende as normas ABNT
    Then eu devo rejeitar o trabalho "Trabalho n° 1002"
    And o sistema deve marcar o trabalo como "revisão pendente" ou "rejeitado"
    And solicitar que o autor realize as devidas correções para que o trabalho se torne conforme para divulgação
