Feature: Gerenciar listas de obras de interesse

  Background: Usuário Autenticado
    Given que eu seja um usuário autenticado no sistema

  Scenario: Criar uma nova pasta de obras de interesse
    Given eu optar por criar uma nova pasta para guardar obras de meu interesse
    And eu fornecer o nome "Leituras de Férias" para a pasta
    Then o sistema deve criar a pasta com o nome "Leituras de Férias"
    And a pasta "Leituras de Férias" deve estar visível na minha lista de pastas
    And a nova pasta deve estar disponível para adicionar obras posteriormente

  Scenario: Adicionar uma obra a uma pasta existente
    Given que eu possua uma pasta de interesse chamada "Clássicos da Literatura"
    And a obra "Dom Casmurro" esteja disponível no catálogo do sistema
    When eu selecionar a opção de adicionar a obra "Dom Casmurro" à pasta "Clássicos da Literatura"
    Then o sistema deve registrar a associação da obra "Dom Casmurro" à pasta "Clássicos da Literatura"
    And a obra "Dom Casmurro" deve aparecer listada dentro da pasta "Clássicos da Literatura"

  Scenario: Visualizar a lista de obras guardadas na pasta
    Given que a pasta "Meus Favoritos de Fantasia" contenha as obras "O Nome do Vento" e "O Hobbit" adicionadas previamente
    When eu acessar a pasta "Meus Favoritos de Fantasia"
    Then o sistema deve exibir as obras "O Nome do Vento" e "O Hobbit" guardadas nessa pasta
    And devo ter a opção de visualizar os detalhes de cada obra listada